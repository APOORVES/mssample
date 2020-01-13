package com.mssample.product.security;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mssample.account.exception.UserNotFoundException;
import com.mssample.product.model.User;

@Service
public class MsSampleUserDetailsService implements UserDetailsService {
 
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${com.mssample.product.getUserDetailsUrl}")
    private String url;
    @Value("${com.mssample.product.username}")
    private String userName;
    @Value("${com.mssample.product.password}")
    private String password;
     
 
    @Override
    public UserDetails loadUserByUsername(String userName) {
      Map<String, String> uriVariables = new HashMap<String, String>();

      String plainCreds = this.userName+":"+this.password;
      byte[] plainCredsBytes = plainCreds.getBytes();
      byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
      String base64Creds = new String(base64CredsBytes);
      HttpHeaders headers = new HttpHeaders();
      headers.add("Authorization", "Basic " + base64Creds);
      HttpEntity<User> request = new HttpEntity<User>(new User(userName), headers);
      
      User user = restTemplate.postForObject(url, request, User.class);
        if (user == null) {
            throw new UserNotFoundException(userName);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<String> userRoles=Arrays.asList("USER");
        return new MsSampleUserDetails(user, userRoles);
    }
}

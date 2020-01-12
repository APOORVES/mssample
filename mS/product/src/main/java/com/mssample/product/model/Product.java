package com.mssample.product.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PRODUCT")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Product {
	@Id
	@Column(name="PRODUCT_ID")
	private String productId;
	@Column(name="DISPLAY_NAME")
	private String displayName;
	@Column(name="SHORT_DESC")
	private String shortDesc;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="CATEGORY")
	private String category;
	@Column(name="PRICE")
	private double price;
	@Column(name="DELIVERY_CHARGE")
	private double deliveryCharge;
	@JsonBackReference
	@OneToOne(mappedBy = "product")
	private Deal deal;
	@Transient
	public double getOfferPrice() {
		return price*deal.getDiscount();
	}
	@ManyToOne
	@JoinColumn(name="SELLER_ID")
	private Seller seller;
	@Transient
	public String getAvgRating() {
		if(ratings != null && !ratings.isEmpty())
		{
			return ""+ratings.stream().collect(Collectors.averagingDouble(Rating::getRating));
		}
		return "";
	}
	@OneToMany(mappedBy = "productId")
	private List<Rating> ratings;


}

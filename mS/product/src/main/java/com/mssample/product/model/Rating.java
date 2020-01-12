package com.mssample.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "RATING")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Rating {
	@JsonIgnore
	@Id
	@Column(name="RATING_ID")
	private String ratingId;
	@Column(name="USER_ID")
	private String userName;
	@Column(name="RATING")
	private double rating;
	@Column(name="REVIEW_COMMENTS")
	private String reviewComments;
	@JsonIgnore
	@Column(name="PRODUCT_ID")
	private String productId;

}

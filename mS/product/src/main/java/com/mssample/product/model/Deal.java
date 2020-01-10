package com.mssample.product.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DEAL")
@Getter @Setter @NoArgsConstructor
public class Deal {
	@Id
	@Column(name="DEAL_ID")
	private String dealId;
	@Column(name="DEAL_NAME")
	private String dealName;
	@JsonManagedReference
	@OneToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
	private Product product;
	@Column(name="START_DATE")
	private Timestamp startDate;
	@Column(name="END_DATE")
	private Timestamp endDate;
	@Column(name="DISCOUNT")
	private double discount;


}

package com.mssample.cart.modelui.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@JsonInclude(Include. NON_NULL)
public class GetCartResponse extends CartResponse {
	private List<CartDetail> cartDetails;
	private double totalPrice;
	private double totalDeliveryCharge;
	private double grandTotal;
	public GetCartResponse(List<CartDetail> cartDetails, double freeDeliveryThreshold) {
		this.cartDetails = cartDetails;
		cartDetails.stream().forEach(cd->{
			this.totalPrice += cd.getCartOfferPrice()*cd.getQuantity();
			this.totalDeliveryCharge += cd.getDeliveryCharge()*cd.getQuantity();
		});
		if(this.totalPrice >= freeDeliveryThreshold)
			this.totalDeliveryCharge = 0;
		this.grandTotal = this.totalPrice+this.totalDeliveryCharge;
	}

}

package com.mssample.cart.modelui.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class GetCartResponse extends CartResponse {
	private List<CartDetail> cartDetails;
	private double totalPrice;
	private double totalDeliveryCharge;
	private double grandTotal;
	public GetCartResponse(List<CartDetail> cartDetails) {
		this.cartDetails = cartDetails;
		cartDetails.stream().forEach(cd->{
			this.totalPrice += cd.getCartOfferPrice();
			this.totalDeliveryCharge += cd.getDeliveryCharge();
		});
		if(this.totalPrice >= 1000)
			this.totalDeliveryCharge = 0;
		this.grandTotal = this.totalPrice+this.totalDeliveryCharge;
	}

}

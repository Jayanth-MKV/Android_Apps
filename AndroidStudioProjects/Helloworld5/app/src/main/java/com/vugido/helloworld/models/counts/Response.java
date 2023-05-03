package com.vugido.helloworld.models.counts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response{

	@JsonProperty("product")
	private int product;

	@JsonProperty("discount")
	private int discount;

	@JsonProperty("category")
	private int category;

	@JsonProperty("discountedprods")
	private int discountedprods;

	@JsonProperty("status")
	private boolean status;

	public void setProduct(int product){
		this.product = product;
	}

	public int getProduct(){
		return product;
	}

	public void setDiscount(int discount){
		this.discount = discount;
	}

	public int getDiscount(){
		return discount;
	}

	public void setCategory(int category){
		this.category = category;
	}

	public int getCategory(){
		return category;
	}

	public void setDiscountedprods(int discountedprods){
		this.discountedprods = discountedprods;
	}

	public int getDiscountedprods(){
		return discountedprods;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"product = '" + product + '\'' + 
			",discount = '" + discount + '\'' + 
			",category = '" + category + '\'' + 
			",discountedprods = '" + discountedprods + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
package com.foodhub.vugido.models.cart.add_to_cart;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {

	@JsonProperty("CC")
	private int cC;

	@JsonProperty("STATUS")
	private boolean sTATUS;

	public void setCC(int cC){
		this.cC = cC;
	}

	public int getCC(){
		return cC;
	}

	public void setSTATUS(boolean sTATUS){
		this.sTATUS = sTATUS;
	}

	public boolean isSTATUS(){
		return sTATUS;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"cC = '" + cC + '\'' + 
			",sTATUS = '" + sTATUS + '\'' + 
			"}";
		}
}
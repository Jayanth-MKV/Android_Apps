package com.vugido.online_groceries.models.order_info;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response{

	@JsonProperty("STATUS")
	private boolean sTATUS;

	@JsonProperty("DATA")
	private DATA dATA;

	public void setSTATUS(boolean sTATUS){
		this.sTATUS = sTATUS;
	}

	public boolean isSTATUS(){
		return sTATUS;
	}

	public void setDATA(DATA dATA){
		this.dATA = dATA;
	}

	public DATA getDATA(){
		return dATA;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"sTATUS = '" + sTATUS + '\'' + 
			",dATA = '" + dATA + '\'' + 
			"}";
		}
}
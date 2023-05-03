package com.dailyneeds.vugido.models.CartDelete;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Response {

	@JsonProperty("STATUS")
	private boolean sTATUS;

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
			"sTATUS = '" + sTATUS + '\'' + 
			"}";
		}
}
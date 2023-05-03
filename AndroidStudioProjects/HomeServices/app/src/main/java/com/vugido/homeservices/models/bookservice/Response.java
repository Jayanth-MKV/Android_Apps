package com.vugido.homeservices.models.bookservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vugido.homeservices.models.user.USER;

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
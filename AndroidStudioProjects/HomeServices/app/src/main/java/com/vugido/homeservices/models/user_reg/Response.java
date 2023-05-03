package com.vugido.homeservices.models.user_reg;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {

	@JsonProperty("USER_EXISTS")
	private boolean uSEREXISTS;

	public void setUSEREXISTS(boolean uSEREXISTS){
		this.uSEREXISTS = uSEREXISTS;
	}

	public boolean isUSEREXISTS(){
		return uSEREXISTS;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"uSER_EXISTS = '" + uSEREXISTS + '\'' + 
			"}";
		}
}
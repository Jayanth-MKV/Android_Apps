package com.vugido.brain_cord.models.quiz_reg;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response{

	@JsonProperty("USER_EXISTS")
	private boolean uSEREXISTS;

	@JsonProperty("USER")
	private USER uSER;

	public void setUSEREXISTS(boolean uSEREXISTS){
		this.uSEREXISTS = uSEREXISTS;
	}

	public boolean isUSEREXISTS(){
		return uSEREXISTS;
	}

	public void setUSER(USER uSER){
		this.uSER = uSER;
	}

	public USER getUSER(){
		return uSER;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"uSER_EXISTS = '" + uSEREXISTS + '\'' + 
			",uSER = '" + uSER + '\'' + 
			"}";
		}
}
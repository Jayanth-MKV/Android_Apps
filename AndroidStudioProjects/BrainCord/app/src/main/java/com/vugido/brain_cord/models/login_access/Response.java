package com.vugido.brain_cord.models.login_access;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response{

	@JsonProperty("SECONDS")
	private int sECONDS;

	public void setSECONDS(int sECONDS){
		this.sECONDS = sECONDS;
	}

	public int getSECONDS(){
		return sECONDS;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"sECONDS = '" + sECONDS + '\'' + 
			"}";
		}
}
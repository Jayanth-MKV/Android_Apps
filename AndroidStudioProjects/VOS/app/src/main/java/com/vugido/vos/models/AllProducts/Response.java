package com.vugido.vos.models.AllProducts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response{

	@JsonProperty("DATA")
	private DATA dATA;

	@JsonProperty("error")
	private boolean error;

	public void setDATA(DATA dATA){
		this.dATA = dATA;
	}

	public DATA getDATA(){
		return dATA;
	}

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
		return error;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"dATA = '" + dATA + '\'' + 
			",error = '" + error + '\'' + 
			"}";
		}
}
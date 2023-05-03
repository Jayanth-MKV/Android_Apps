package com.vugido.foodhub.v_dm.models.status;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {

	@JsonProperty("error")
	private boolean error;

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
			"error = '" + error + '\'' + 
			"}";
		}
}
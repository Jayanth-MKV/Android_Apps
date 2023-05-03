package com.dailyneeds.vugido.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Error{

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
			"Error{" + 
			"error = '" + error + '\'' + 
			"}";
		}
}
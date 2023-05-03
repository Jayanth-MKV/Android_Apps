package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.updated.login;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response{

	@JsonProperty("error")
	private boolean error;

	@JsonProperty("message")
	private String message;

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
		return error;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}
package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.updated.login.verify_data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response{

	@JsonProperty("profile")
	private Profile profile;

	@JsonProperty("error")
	private boolean error;

	@JsonProperty("message")
	private String message;

	public void setProfile(Profile profile){
		this.profile = profile;
	}

	public Profile getProfile(){
		return profile;
	}

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
			"profile = '" + profile + '\'' + 
			",error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}
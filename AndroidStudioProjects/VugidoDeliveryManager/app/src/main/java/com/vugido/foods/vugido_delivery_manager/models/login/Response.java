package com.vugido.foods.vugido_delivery_manager.models.login;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Response{

	@JsonProperty("error")
	private boolean error;

	@JsonProperty("Profile")
	private List<ProfileItem> profile;

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
		return error;
	}

	public void setProfile(List<ProfileItem> profile){
		this.profile = profile;
	}

	public List<ProfileItem> getProfile(){
		return profile;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"error = '" + error + '\'' + 
			",profile = '" + profile + '\'' + 
			"}";
		}
}
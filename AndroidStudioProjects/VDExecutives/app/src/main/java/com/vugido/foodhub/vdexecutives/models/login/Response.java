package com.vugido.foodhub.vdexecutives.models.login;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

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
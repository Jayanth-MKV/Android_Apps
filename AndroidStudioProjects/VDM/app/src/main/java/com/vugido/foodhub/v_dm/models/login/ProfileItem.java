package com.vugido.foodhub.v_dm.models.login;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfileItem{

	@JsonProperty("MID")
	private int mID;

	public void setMID(int mID){
		this.mID = mID;
	}

	public int getMID(){
		return mID;
	}

	@Override
 	public String toString(){
		return 
			"ProfileItem{" + 
			"mID = '" + mID + '\'' + 
			"}";
		}
}
package com.vugido.online_groceries.models.cart;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ABSENTItem{

	@JsonProperty("PID")
	private String pID;

	@JsonProperty("CID")
	private String cID;

	public void setPID(String pID){
		this.pID = pID;
	}

	public String getPID(){
		return pID;
	}

	public void setCID(String cID){
		this.cID = cID;
	}

	public String getCID(){
		return cID;
	}

	@Override
 	public String toString(){
		return 
			"ABSENTItem{" + 
			"pID = '" + pID + '\'' + 
			",cID = '" + cID + '\'' + 
			"}";
		}
}
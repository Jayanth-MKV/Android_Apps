package com.vugido.com.vugidoeats.models.login_sign_up_response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {

	@JsonProperty("ERROR")
	private boolean eRROR;

	@JsonProperty("ID")
	private int iD;

	public void setERROR(boolean eRROR){
		this.eRROR = eRROR;
	}

	public boolean isERROR(){
		return eRROR;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"eRROR = '" + eRROR + '\'' + 
			",iD = '" + iD + '\'' + 
			"}";
		}
}
package com.vugido.com.vugidoeats.models.cart;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CATEGORIESItem{

	@JsonProperty("CNAME")
	private String cNAME;

	@JsonProperty("SID")
	private int sID;

	public void setCNAME(String cNAME){
		this.cNAME = cNAME;
	}

	public String getCNAME(){
		return cNAME;
	}

	public void setSID(int sID){
		this.sID = sID;
	}

	public int getSID(){
		return sID;
	}

	@Override
 	public String toString(){
		return 
			"CATEGORIESItem{" + 
			"cNAME = '" + cNAME + '\'' + 
			",sID = '" + sID + '\'' + 
			"}";
		}
}
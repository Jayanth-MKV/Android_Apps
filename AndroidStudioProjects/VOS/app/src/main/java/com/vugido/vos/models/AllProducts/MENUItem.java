package com.vugido.vos.models.AllProducts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MENUItem{

	@JsonProperty("SUB_NAME")
	private String sUBNAME;

	@JsonProperty("CID")
	private int cID;

	public void setSUBNAME(String sUBNAME){
		this.sUBNAME = sUBNAME;
	}

	public String getSUBNAME(){
		return sUBNAME;
	}

	public void setCID(int cID){
		this.cID = cID;
	}

	public int getCID(){
		return cID;
	}

	@Override
 	public String toString(){
		return 
			"MENUItem{" + 
			"sUB_NAME = '" + sUBNAME + '\'' + 
			",cID = '" + cID + '\'' + 
			"}";
		}
}
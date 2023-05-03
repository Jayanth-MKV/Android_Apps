package com.foodhub.vugido.models.product.client_products;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CATEGORIESItem{

	@JsonProperty("SUB_CAT_ID")
	private String sUBCATID;

	@JsonProperty("ID")
	private int iD;

	public void setSUBCATID(String sUBCATID){
		this.sUBCATID = sUBCATID;
	}

	public String getSUBCATID(){
		return sUBCATID;
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
			"CATEGORIESItem{" + 
			"sUB_CAT_ID = '" + sUBCATID + '\'' + 
			",iD = '" + iD + '\'' + 
			"}";
		}
}
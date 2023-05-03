package com.dailyneeds.vugido.models.SubCategoryModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class SubMenuItem {

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
			"SubMenuItem{" +
			"sUB_NAME = '" + sUBNAME + '\'' + 
			",cID = '" + cID + '\'' + 
			"}";
		}
}
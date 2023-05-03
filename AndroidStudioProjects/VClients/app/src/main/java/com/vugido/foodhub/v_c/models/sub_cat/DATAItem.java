package com.vugido.foodhub.v_c.models.sub_cat;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("SUB_NAME")
	private String sUBNAME;

	@JsonProperty("ID")
	private int iD;

	public void setSUBNAME(String sUBNAME){
		this.sUBNAME = sUBNAME;
	}

	public String getSUBNAME(){
		return sUBNAME;
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
			"DATAItem{" + 
			"sUB_NAME = '" + sUBNAME + '\'' + 
			",iD = '" + iD + '\'' + 
			"}";
		}
}
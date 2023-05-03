package com.vugido.vos.models.Categories;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("CATEGORY")
	private String cATEGORY;

	@JsonProperty("ID")
	private int iD;

	@JsonProperty("ICONS")
	private String iCONS;

	public void setCATEGORY(String cATEGORY){
		this.cATEGORY = cATEGORY;
	}

	public String getCATEGORY(){
		return cATEGORY;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setICONS(String iCONS){
		this.iCONS = iCONS;
	}

	public String getICONS(){
		return iCONS;
	}

	@Override
 	public String toString(){
		return 
			"DATAItem{" + 
			"cATEGORY = '" + cATEGORY + '\'' + 
			",iD = '" + iD + '\'' + 
			",iCONS = '" + iCONS + '\'' + 
			"}";
		}
}
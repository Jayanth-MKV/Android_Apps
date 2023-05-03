package com.vugido.foodhub.hungrybirdsadmin.models.homepage.slider;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("IMG")
	private String iMG;

	@JsonProperty("ID")
	private int iD;

	@JsonProperty("HS")
	private int hS;

	public void setIMG(String iMG){
		this.iMG = iMG;
	}

	public String getIMG(){
		return iMG;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setHS(int hS){
		this.hS = hS;
	}

	public int getHS(){
		return hS;
	}

	@Override
 	public String toString(){
		return 
			"DATAItem{" + 
			"iMG = '" + iMG + '\'' + 
			",iD = '" + iD + '\'' + 
			",hS = '" + hS + '\'' + 
			"}";
		}
}
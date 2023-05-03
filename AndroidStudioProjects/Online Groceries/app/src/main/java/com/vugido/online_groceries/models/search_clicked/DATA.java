package com.vugido.online_groceries.models.search_clicked;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATA{

	@JsonProperty("CATEGORY")
	private int cATEGORY;

	@JsonProperty("PRODUCT")
	private PRODUCT pRODUCT;

	@JsonProperty("SEARCH_KEY")
	private String sEARCHKEY;

	public void setCATEGORY(int cATEGORY){
		this.cATEGORY = cATEGORY;
	}

	public int getCATEGORY(){
		return cATEGORY;
	}

	public void setPRODUCT(PRODUCT pRODUCT){
		this.pRODUCT = pRODUCT;
	}

	public PRODUCT getPRODUCT(){
		return pRODUCT;
	}

	public void setSEARCHKEY(String sEARCHKEY){
		this.sEARCHKEY = sEARCHKEY;
	}

	public String getSEARCHKEY(){
		return sEARCHKEY;
	}

	@Override
 	public String toString(){
		return 
			"DATA{" + 
			"cATEGORY = '" + cATEGORY + '\'' + 
			",pRODUCT = '" + pRODUCT + '\'' + 
			",sEARCH_KEY = '" + sEARCHKEY + '\'' + 
			"}";
		}
}
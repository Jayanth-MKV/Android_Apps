package com.vugido.online_groceries.models.search_indexer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("IMAGE")
	private String iMAGE;

	@JsonProperty("SEARCH_ID")
	private int sEARCHID;

	@JsonProperty("IS_CATEGORY")
	private int iSCATEGORY;

	@JsonProperty("SEARCH_KEY")
	private String sEARCHKEY;

	@JsonProperty("KEY")
	private String kEY;

	@JsonProperty("SID")
	private int sID;

	public void setIMAGE(String iMAGE){
		this.iMAGE = iMAGE;
	}

	public String getIMAGE(){
		return iMAGE;
	}

	public void setSEARCHID(int sEARCHID){
		this.sEARCHID = sEARCHID;
	}

	public int getSEARCHID(){
		return sEARCHID;
	}

	public void setISCATEGORY(int iSCATEGORY){
		this.iSCATEGORY = iSCATEGORY;
	}

	public int getISCATEGORY(){
		return iSCATEGORY;
	}

	public void setSEARCHKEY(String sEARCHKEY){
		this.sEARCHKEY = sEARCHKEY;
	}

	public String getSEARCHKEY(){
		return sEARCHKEY;
	}

	public void setKEY(String kEY){
		this.kEY = kEY;
	}

	public String getKEY(){
		return kEY;
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
			"DATAItem{" + 
			"iMAGE = '" + iMAGE + '\'' + 
			",sEARCH_ID = '" + sEARCHID + '\'' + 
			",iS_CATEGORY = '" + iSCATEGORY + '\'' + 
			",sEARCH_KEY = '" + sEARCHKEY + '\'' + 
			",kEY = '" + kEY + '\'' + 
			",sID = '" + sID + '\'' + 
			"}";
		}
}
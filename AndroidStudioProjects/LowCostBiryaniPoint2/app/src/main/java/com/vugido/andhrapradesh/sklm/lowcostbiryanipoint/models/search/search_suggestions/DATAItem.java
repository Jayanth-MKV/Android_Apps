package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.search.search_suggestions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("IMAGE")
	private String iMAGE;

	@JsonProperty("SEARCH_ID")
	private int sEARCHID;

	@JsonProperty("SEARCH_KEY")
	private String sEARCHKEY;

	@JsonProperty("CID")
	private int cID;


	public void setCID(int cID){
		this.cID=cID;
	}
	public int getCID(){
		return cID;
	}

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

	public void setSEARCHKEY(String sEARCHKEY){
		this.sEARCHKEY = sEARCHKEY;
	}

	public String getSEARCHKEY(){
		return sEARCHKEY;
	}

	@Override
 	public String toString(){
		return 
			"DATAItem{" + 
			"iMAGE = '" + iMAGE + '\'' + 
			",sEARCH_ID = '" + sEARCHID + '\'' + 
			",sEARCH_KEY = '" + sEARCHKEY + '\'' +
					",cID = '" + cID + '\'' +
					"}";
		}
}
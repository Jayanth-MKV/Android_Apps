package com.vugido.online_groceries.models.homepage.updated;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CATEGORIESItem{

	@JsonProperty("IMAGE")
	private String iMAGE;

	@JsonProperty("TITLE")
	private String tITLE;

	@JsonProperty("ID")
	private int iD;

	public void setIMAGE(String iMAGE){
		this.iMAGE = iMAGE;
	}

	public String getIMAGE(){
		return iMAGE;
	}

	public void setTITLE(String tITLE){
		this.tITLE = tITLE;
	}

	public String getTITLE(){
		return tITLE;
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
			"iMAGE = '" + iMAGE + '\'' + 
			",tITLE = '" + tITLE + '\'' + 
			",iD = '" + iD + '\'' + 
			"}";
		}
}
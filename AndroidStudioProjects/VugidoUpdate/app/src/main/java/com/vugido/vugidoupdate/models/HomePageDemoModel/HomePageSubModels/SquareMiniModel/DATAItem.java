package com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SquareMiniModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("IMAGE")
	private String iMAGE;

	@JsonProperty("ID")
	private int iD;

	@JsonProperty("TID")
	private int tID;

	@JsonProperty("TAG_NAME")
	private String tAGNAME;

	public void setIMAGE(String iMAGE){
		this.iMAGE = iMAGE;
	}

	public String getIMAGE(){
		return iMAGE;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setTID(int tID){
		this.tID = tID;
	}

	public int getTID(){
		return tID;
	}

	public void setTAGNAME(String tAGNAME){
		this.tAGNAME = tAGNAME;
	}

	public String getTAGNAME(){
		return tAGNAME;
	}

	@Override
 	public String toString(){
		return 
			"DATAItem{" + 
			"iMAGE = '" + iMAGE + '\'' + 
			",iD = '" + iD + '\'' + 
			",tID = '" + tID + '\'' + 
			",tAG_NAME = '" + tAGNAME + '\'' + 
			"}";
		}
}
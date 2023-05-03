package com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.CircularViewModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("TYPE_ID")
	private int tYPEID;

	@JsonProperty("IMAGE")
	private String iMAGE;

	@JsonProperty("TYPE_NAME")
	private String tYPENAME;

	public void setTYPEID(int tYPEID){
		this.tYPEID = tYPEID;
	}

	public int getTYPEID(){
		return tYPEID;
	}

	public void setIMAGE(String iMAGE){
		this.iMAGE = iMAGE;
	}

	public String getIMAGE(){
		return iMAGE;
	}

	public void setTYPENAME(String tYPENAME){
		this.tYPENAME = tYPENAME;
	}

	public String getTYPENAME(){
		return tYPENAME;
	}

	@Override
 	public String toString(){
		return 
			"DATAItem{" + 
			"tYPE_ID = '" + tYPEID + '\'' + 
			",iMAGE = '" + iMAGE + '\'' + 
			",tYPE_NAME = '" + tYPENAME + '\'' + 
			"}";
		}
}
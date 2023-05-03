package com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SliderViewModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("IMAGE")
	private String iMAGE;

	@JsonProperty("ID")
	private int iD;

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

	@Override
 	public String toString(){
		return 
			"DATAItem{" + 
			"iMAGE = '" + iMAGE + '\'' + 
			",iD = '" + iD + '\'' + 
			"}";
		}
}
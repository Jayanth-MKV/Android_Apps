package com.vugido.homeservices.models.homepage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SLIDERItem{

	@JsonProperty("IMAGE")
	private String iMAGE;

	@JsonProperty("SID")
	private int sID;

	public void setIMAGE(String iMAGE){
		this.iMAGE = iMAGE;
	}

	public String getIMAGE(){
		return iMAGE;
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
			"SLIDERItem{" + 
			"iMAGE = '" + iMAGE + '\'' + 
			",sID = '" + sID + '\'' + 
			"}";
		}
}
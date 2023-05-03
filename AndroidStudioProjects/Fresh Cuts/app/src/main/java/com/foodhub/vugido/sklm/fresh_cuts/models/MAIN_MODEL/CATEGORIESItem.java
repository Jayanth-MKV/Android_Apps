package com.foodhub.vugido.sklm.fresh_cuts.models.MAIN_MODEL;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CATEGORIESItem{

	@JsonProperty("IMAGE")
	private String iMAGE;

	@JsonProperty("CNAME")
	private String cNAME;

	@JsonProperty("SID")
	private int sID;

	public void setIMAGE(String iMAGE){
		this.iMAGE = iMAGE;
	}

	public String getIMAGE(){
		return iMAGE;
	}

	public void setCNAME(String cNAME){
		this.cNAME = cNAME;
	}

	public String getCNAME(){
		return cNAME;
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
			"CATEGORIESItem{" + 
			"iMAGE = '" + iMAGE + '\'' + 
			",cNAME = '" + cNAME + '\'' + 
			",sID = '" + sID + '\'' + 
			"}";
		}
}
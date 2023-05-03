package com.foodhub.vugido.sklm.fresh_cuts.models.MAIN_MODEL;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HIGHTLIGHTItem{

	@JsonProperty("IMAGE")
	private String iMAGE;

	@JsonProperty("DESCRIPTION")
	private String dESCRIPTION;

	@JsonProperty("PID")
	private int pID;

	public void setIMAGE(String iMAGE){
		this.iMAGE = iMAGE;
	}

	public String getIMAGE(){
		return iMAGE;
	}

	public void setDESCRIPTION(String dESCRIPTION){
		this.dESCRIPTION = dESCRIPTION;
	}

	public String getDESCRIPTION(){
		return dESCRIPTION;
	}

	public void setPID(int pID){
		this.pID = pID;
	}

	public int getPID(){
		return pID;
	}

	@Override
 	public String toString(){
		return 
			"HIGHTLIGHTItem{" + 
			"iMAGE = '" + iMAGE + '\'' + 
			",dESCRIPTION = '" + dESCRIPTION + '\'' + 
			",pID = '" + pID + '\'' + 
			"}";
		}
}
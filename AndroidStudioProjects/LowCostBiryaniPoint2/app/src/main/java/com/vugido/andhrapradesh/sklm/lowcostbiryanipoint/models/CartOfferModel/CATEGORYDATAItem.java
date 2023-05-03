package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.CartOfferModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CATEGORYDATAItem{

	@JsonProperty("TARGET")
	private int tARGET;

	@JsonProperty("HID")
	private int hID;

	@JsonProperty("CATEGORY_VALUE")
	private int cATEGORYVALUE;

	@JsonProperty("REWARD")
	private REWARD rEWARD;

	public void setTARGET(int tARGET){
		this.tARGET = tARGET;
	}

	public int getTARGET(){
		return tARGET;
	}

	public void setHID(int hID){
		this.hID = hID;
	}

	public int getHID(){
		return hID;
	}

	public void setCATEGORYVALUE(int cATEGORYVALUE){
		this.cATEGORYVALUE = cATEGORYVALUE;
	}

	public int getCATEGORYVALUE(){
		return cATEGORYVALUE;
	}

	public void setREWARD(REWARD rEWARD){
		this.rEWARD = rEWARD;
	}

	public REWARD getREWARD(){
		return rEWARD;
	}

	@Override
 	public String toString(){
		return 
			"CATEGORYDATAItem{" + 
			"tARGET = '" + tARGET + '\'' + 
			",hID = '" + hID + '\'' + 
			",cATEGORY_VALUE = '" + cATEGORYVALUE + '\'' + 
			",rEWARD = '" + rEWARD + '\'' + 
			"}";
		}
}
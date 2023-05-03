package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.CartOfferModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CARTDATA{

	@JsonProperty("TARGET")
	private int tARGET;

	@JsonProperty("HID")
	private int hID;

	@JsonProperty("CART_VALUE")
	private int cARTVALUE;

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

	public void setCARTVALUE(int cARTVALUE){
		this.cARTVALUE = cARTVALUE;
	}

	public int getCARTVALUE(){
		return cARTVALUE;
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
			"CARTDATA{" + 
			"tARGET = '" + tARGET + '\'' + 
			",hID = '" + hID + '\'' + 
			",cART_VALUE = '" + cARTVALUE + '\'' + 
			",rEWARD = '" + rEWARD + '\'' + 
			"}";
		}
}
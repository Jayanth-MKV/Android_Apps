package com.foodhub.vugido.models.check_o;

import com.fasterxml.jackson.annotation.JsonProperty;

public class COUPON{

	@JsonProperty("COUPON_TAG")
	private String cOUPONTAG;

	@JsonProperty("EXISTS")
	private boolean eXISTS;

	@JsonProperty("COUPON_THRESHOLD")
	private int cOUPONTHRESHOLD;

	@JsonProperty("COUPON_VALUE")
	private int cOUPONVALUE;

	@JsonProperty("COUPON_IMAGE")
	private String cOUPONIMAGE;

	@JsonProperty("COUPON_ID")
	private int cOUPONID;

	public void setCOUPONTAG(String cOUPONTAG){
		this.cOUPONTAG = cOUPONTAG;
	}

	public String getCOUPONTAG(){
		return cOUPONTAG;
	}

	public void setEXISTS(boolean eXISTS){
		this.eXISTS = eXISTS;
	}

	public boolean isEXISTS(){
		return eXISTS;
	}

	public void setCOUPONTHRESHOLD(int cOUPONTHRESHOLD){
		this.cOUPONTHRESHOLD = cOUPONTHRESHOLD;
	}

	public int getCOUPONTHRESHOLD(){
		return cOUPONTHRESHOLD;
	}

	public void setCOUPONVALUE(int cOUPONVALUE){
		this.cOUPONVALUE = cOUPONVALUE;
	}

	public int getCOUPONVALUE(){
		return cOUPONVALUE;
	}

	public void setCOUPONIMAGE(String cOUPONIMAGE){
		this.cOUPONIMAGE = cOUPONIMAGE;
	}

	public String getCOUPONIMAGE(){
		return cOUPONIMAGE;
	}

	public void setCOUPONID(int cOUPONID){
		this.cOUPONID = cOUPONID;
	}

	public int getCOUPONID(){
		return cOUPONID;
	}

	@Override
 	public String toString(){
		return 
			"COUPON{" + 
			"cOUPON_TAG = '" + cOUPONTAG + '\'' + 
			",eXISTS = '" + eXISTS + '\'' + 
			",cOUPON_THRESHOLD = '" + cOUPONTHRESHOLD + '\'' + 
			",cOUPON_VALUE = '" + cOUPONVALUE + '\'' + 
			",cOUPON_IMAGE = '" + cOUPONIMAGE + '\'' + 
			",cOUPON_ID = '" + cOUPONID + '\'' + 
			"}";
		}
}
package com.vugido.vugidoccs.models.InActiveUsers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("DATE")
	private String dATE;

	@JsonProperty("ORDER_ID")
	private int oRDERID;

	@JsonProperty("PHONE")
	private String pHONE;

	@JsonProperty("USER_ID")
	private int uSERID;

	@JsonProperty("TIME")
	private String tIME;

	@JsonProperty("USER_NAME")
	private String uSERNAME;

	public void setDATE(String dATE){
		this.dATE = dATE;
	}

	public String getDATE(){
		return dATE;
	}

	public void setORDERID(int oRDERID){
		this.oRDERID = oRDERID;
	}

	public int getORDERID(){
		return oRDERID;
	}

	public void setPHONE(String pHONE){
		this.pHONE = pHONE;
	}

	public String getPHONE(){
		return pHONE;
	}

	public void setUSERID(int uSERID){
		this.uSERID = uSERID;
	}

	public int getUSERID(){
		return uSERID;
	}

	public void setTIME(String tIME){
		this.tIME = tIME;
	}

	public String getTIME(){
		return tIME;
	}

	public void setUSERNAME(String uSERNAME){
		this.uSERNAME = uSERNAME;
	}

	public String getUSERNAME(){
		return uSERNAME;
	}

	@Override
 	public String toString(){
		return 
			"DATAItem{" + 
			"dATE = '" + dATE + '\'' + 
			",oRDER_ID = '" + oRDERID + '\'' + 
			",pHONE = '" + pHONE + '\'' + 
			",uSER_ID = '" + uSERID + '\'' + 
			",tIME = '" + tIME + '\'' + 
			",uSER_NAME = '" + uSERNAME + '\'' + 
			"}";
		}
}
package com.vugido.vos.models.Analytics.CustomOrders;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("DATE")
	private String dATE;

	@JsonProperty("PHONE")
	private String pHONE;

	@JsonProperty("USER_ID")
	private int uSERID;

	@JsonProperty("TIME")
	private String tIME;

	@JsonProperty("ID")
	private int iD;

	public void setDATE(String dATE){
		this.dATE = dATE;
	}

	public String getDATE(){
		return dATE;
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
			"dATE = '" + dATE + '\'' + 
			",pHONE = '" + pHONE + '\'' + 
			",uSER_ID = '" + uSERID + '\'' + 
			",tIME = '" + tIME + '\'' + 
			",iD = '" + iD + '\'' + 
			"}";
		}
}
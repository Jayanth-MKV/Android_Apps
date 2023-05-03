package com.vugido.vugidoccs.models.OrderCountUsers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("UID")
	private int uID;

	@JsonProperty("PHONE")
	private String pHONE;

	@JsonProperty("ORDERS")
	private int oRDERS;

	@JsonProperty("USERNAME")
	private String uSERNAME;

	public void setUID(int uID){
		this.uID = uID;
	}

	public int getUID(){
		return uID;
	}

	public void setPHONE(String pHONE){
		this.pHONE = pHONE;
	}

	public String getPHONE(){
		return pHONE;
	}

	public void setORDERS(int oRDERS){
		this.oRDERS = oRDERS;
	}

	public int getORDERS(){
		return oRDERS;
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
			"uID = '" + uID + '\'' + 
			",pHONE = '" + pHONE + '\'' + 
			",oRDERS = '" + oRDERS + '\'' + 
			",uSERNAME = '" + uSERNAME + '\'' + 
			"}";
		}
}
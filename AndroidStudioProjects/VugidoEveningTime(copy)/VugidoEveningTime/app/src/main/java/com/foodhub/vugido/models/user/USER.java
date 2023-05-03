package com.foodhub.vugido.models.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class USER{

	@JsonProperty("UID")
	private int uID;

	@JsonProperty("PHONE")
	private String pHONE;

	@JsonProperty("CREATED_ON")
	private String cREATEDON;

	@JsonProperty("USERNAME")
	private String uSERNAME;

	@JsonProperty("GMAIL")
	private String gMAIL;

	public String getgMAIL() {
		return gMAIL;
	}

	public void setgMAIL(String gMAIL) {
		this.gMAIL = gMAIL;
	}

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

	public void setCREATEDON(String cREATEDON){
		this.cREATEDON = cREATEDON;
	}

	public String getCREATEDON(){
		return cREATEDON;
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
			"USER{" + 
			"uID = '" + uID + '\'' + 
			",pHONE = '" + pHONE + '\'' + 
			",cREATED_ON = '" + cREATEDON + '\'' + 
			",uSERNAME = '" + uSERNAME + '\'' +
					",gMAIL = '" + gMAIL + '\'' +
					"}";
		}
}
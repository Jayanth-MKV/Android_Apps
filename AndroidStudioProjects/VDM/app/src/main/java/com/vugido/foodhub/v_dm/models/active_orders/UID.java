package com.vugido.foodhub.v_dm.models.active_orders;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UID{

	@JsonProperty("UID")
	private int uID;

	@JsonProperty("GMAIL")
	private String gMAIL;

	@JsonProperty("PHONE")
	private String pHONE;

	@JsonProperty("CREATED_ON")
	private String cREATEDON;

	@JsonProperty("USERNAME")
	private String uSERNAME;

	public void setUID(int uID){
		this.uID = uID;
	}

	public int getUID(){
		return uID;
	}

	public void setGMAIL(String gMAIL){
		this.gMAIL = gMAIL;
	}

	public String getGMAIL(){
		return gMAIL;
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
			"UID{" + 
			"uID = '" + uID + '\'' + 
			",gMAIL = '" + gMAIL + '\'' + 
			",pHONE = '" + pHONE + '\'' + 
			",cREATED_ON = '" + cREATEDON + '\'' + 
			",uSERNAME = '" + uSERNAME + '\'' + 
			"}";
		}
}
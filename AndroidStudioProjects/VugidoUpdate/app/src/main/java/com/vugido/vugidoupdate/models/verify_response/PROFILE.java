package com.vugido.vugidoupdate.models.verify_response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PROFILE{

	@JsonProperty("UID")
	private int uID;

	@JsonProperty("REFERRAL_CODE")
	private String rEFERRALCODE;

	@JsonProperty("PHONE")
	private String pHONE;

	@JsonProperty("USERNAME")
	private String uSERNAME;

	@JsonProperty("CREADTED_ON")
	private String cREADTEDON;

	public void setUID(int uID){
		this.uID = uID;
	}

	public int getUID(){
		return uID;
	}

	public void setREFERRALCODE(String rEFERRALCODE){
		this.rEFERRALCODE = rEFERRALCODE;
	}

	public String getREFERRALCODE(){
		return rEFERRALCODE;
	}

	public void setPHONE(String pHONE){
		this.pHONE = pHONE;
	}

	public String getPHONE(){
		return pHONE;
	}

	public void setUSERNAME(String uSERNAME){
		this.uSERNAME = uSERNAME;
	}

	public String getUSERNAME(){
		return uSERNAME;
	}

	public void setCREADTEDON(String cREADTEDON){
		this.cREADTEDON = cREADTEDON;
	}

	public String getCREADTEDON(){
		return cREADTEDON;
	}

	@Override
 	public String toString(){
		return 
			"PROFILE{" + 
			"uID = '" + uID + '\'' + 
			",rEFERRAL_CODE = '" + rEFERRALCODE + '\'' + 
			",pHONE = '" + pHONE + '\'' + 
			",uSERNAME = '" + uSERNAME + '\'' + 
			",cREADTED_ON = '" + cREADTEDON + '\'' + 
			"}";
		}
}
package com.vugido.brain_cord.models.quiz_reg;

import com.fasterxml.jackson.annotation.JsonProperty;

public class USER{

	@JsonProperty("UID")
	private int uID;

	@JsonProperty("GMAIL")
	private String gMAIL;

	@JsonProperty("LOGGED")
	private int lOGGED;

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

	public void setLOGGED(int lOGGED){
		this.lOGGED = lOGGED;
	}

	public int getLOGGED(){
		return lOGGED;
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
			",gMAIL = '" + gMAIL + '\'' + 
			",lOGGED = '" + lOGGED + '\'' + 
			",uSERNAME = '" + uSERNAME + '\'' + 
			"}";
		}
}
package com.foodhub.vugido.models.coins;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataItem{

	@JsonProperty("IS_CREDIT")
	private int iSCREDIT;

	@JsonProperty("COINS")
	private int cOINS;

	@JsonProperty("DATED")
	private String dATED;

	@JsonProperty("OID")
	private int oID;

	@JsonProperty("REASON")
	private String rEASON;

	@JsonProperty("TID")
	private int tID;

	public void setISCREDIT(int iSCREDIT){
		this.iSCREDIT = iSCREDIT;
	}

	public int getISCREDIT(){
		return iSCREDIT;
	}

	public void setCOINS(int cOINS){
		this.cOINS = cOINS;
	}

	public int getCOINS(){
		return cOINS;
	}

	public void setDATED(String dATED){
		this.dATED = dATED;
	}

	public String getDATED(){
		return dATED;
	}

	public void setOID(int oID){
		this.oID = oID;
	}

	public int getOID(){
		return oID;
	}

	public void setREASON(String rEASON){
		this.rEASON = rEASON;
	}

	public String getREASON(){
		return rEASON;
	}

	public void setTID(int tID){
		this.tID = tID;
	}

	public int getTID(){
		return tID;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"iS_CREDIT = '" + iSCREDIT + '\'' + 
			",cOINS = '" + cOINS + '\'' + 
			",dATED = '" + dATED + '\'' + 
			",oID = '" + oID + '\'' + 
			",rEASON = '" + rEASON + '\'' + 
			",tID = '" + tID + '\'' + 
			"}";
		}
}
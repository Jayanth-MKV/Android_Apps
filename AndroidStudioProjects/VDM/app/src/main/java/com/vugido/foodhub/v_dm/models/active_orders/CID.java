package com.vugido.foodhub.v_dm.models.active_orders;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CID{

	@JsonProperty("LOCATION")
	private String lOCATION;

	@JsonProperty("CONTACT")
	private String cONTACT;

	@JsonProperty("ACTIVE")
	private int aCTIVE;

	@JsonProperty("BUSINESS_NAME")
	private String bUSINESSNAME;

	@JsonProperty("LONGITUDE")
	private String lONGITUDE;

	@JsonProperty("LOGO")
	private String lOGO;

	@JsonProperty("DATED")
	private String dATED;

	@JsonProperty("LATITUDE")
	private String lATITUDE;

	@JsonProperty("CID")
	private int cID;

	public void setLOCATION(String lOCATION){
		this.lOCATION = lOCATION;
	}

	public String getLOCATION(){
		return lOCATION;
	}

	public void setCONTACT(String cONTACT){
		this.cONTACT = cONTACT;
	}

	public String getCONTACT(){
		return cONTACT;
	}

	public void setACTIVE(int aCTIVE){
		this.aCTIVE = aCTIVE;
	}

	public int getACTIVE(){
		return aCTIVE;
	}

	public void setBUSINESSNAME(String bUSINESSNAME){
		this.bUSINESSNAME = bUSINESSNAME;
	}

	public String getBUSINESSNAME(){
		return bUSINESSNAME;
	}

	public void setLONGITUDE(String lONGITUDE){
		this.lONGITUDE = lONGITUDE;
	}

	public String getLONGITUDE(){
		return lONGITUDE;
	}

	public void setLOGO(String lOGO){
		this.lOGO = lOGO;
	}

	public String getLOGO(){
		return lOGO;
	}

	public void setDATED(String dATED){
		this.dATED = dATED;
	}

	public String getDATED(){
		return dATED;
	}

	public void setLATITUDE(String lATITUDE){
		this.lATITUDE = lATITUDE;
	}

	public String getLATITUDE(){
		return lATITUDE;
	}

	public void setCID(int cID){
		this.cID = cID;
	}

	public int getCID(){
		return cID;
	}

	@Override
 	public String toString(){
		return 
			"CID{" + 
			"lOCATION = '" + lOCATION + '\'' + 
			",cONTACT = '" + cONTACT + '\'' + 
			",aCTIVE = '" + aCTIVE + '\'' + 
			",bUSINESS_NAME = '" + bUSINESSNAME + '\'' + 
			",lONGITUDE = '" + lONGITUDE + '\'' + 
			",lOGO = '" + lOGO + '\'' + 
			",dATED = '" + dATED + '\'' + 
			",lATITUDE = '" + lATITUDE + '\'' + 
			",cID = '" + cID + '\'' + 
			"}";
		}
}
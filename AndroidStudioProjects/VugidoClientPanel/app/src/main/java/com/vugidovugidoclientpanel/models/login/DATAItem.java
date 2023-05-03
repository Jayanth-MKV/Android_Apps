package com.vugidovugidoclientpanel.models.login;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("CONTACT")
	private String cONTACT;

	@JsonProperty("ACTIVE_STATE")
	private int aCTIVESTATE;

	@JsonProperty("PASSWORD")
	private String pASSWORD;

	@JsonProperty("BUSINESS_NAME")
	private String bUSINESSNAME;

	@JsonProperty("LONGITUDE")
	private String lONGITUDE;

	@JsonProperty("LOGO")
	private String lOGO;

	@JsonProperty("CREATED_ON")
	private String cREATEDON;

	@JsonProperty("EMAIL_ID")
	private String eMAILID;

	@JsonProperty("ID")
	private int iD;

	@JsonProperty("LOCATION_NAME")
	private String lOCATIONNAME;

	@JsonProperty("LATITUDE")
	private String lATITUDE;

	@JsonProperty("NAME")
	private String nAME;

	public void setCONTACT(String cONTACT){
		this.cONTACT = cONTACT;
	}

	public String getCONTACT(){
		return cONTACT;
	}

	public void setACTIVESTATE(int aCTIVESTATE){
		this.aCTIVESTATE = aCTIVESTATE;
	}

	public int getACTIVESTATE(){
		return aCTIVESTATE;
	}

	public void setPASSWORD(String pASSWORD){
		this.pASSWORD = pASSWORD;
	}

	public String getPASSWORD(){
		return pASSWORD;
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

	public void setCREATEDON(String cREATEDON){
		this.cREATEDON = cREATEDON;
	}

	public String getCREATEDON(){
		return cREATEDON;
	}

	public void setEMAILID(String eMAILID){
		this.eMAILID = eMAILID;
	}

	public String getEMAILID(){
		return eMAILID;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setLOCATIONNAME(String lOCATIONNAME){
		this.lOCATIONNAME = lOCATIONNAME;
	}

	public String getLOCATIONNAME(){
		return lOCATIONNAME;
	}

	public void setLATITUDE(String lATITUDE){
		this.lATITUDE = lATITUDE;
	}

	public String getLATITUDE(){
		return lATITUDE;
	}

	public void setNAME(String nAME){
		this.nAME = nAME;
	}

	public String getNAME(){
		return nAME;
	}

	@Override
 	public String toString(){
		return 
			"DATAItem{" + 
			"cONTACT = '" + cONTACT + '\'' + 
			",aCTIVE_STATE = '" + aCTIVESTATE + '\'' + 
			",pASSWORD = '" + pASSWORD + '\'' + 
			",bUSINESS_NAME = '" + bUSINESSNAME + '\'' + 
			",lONGITUDE = '" + lONGITUDE + '\'' + 
			",lOGO = '" + lOGO + '\'' + 
			",cREATED_ON = '" + cREATEDON + '\'' + 
			",eMAIL_ID = '" + eMAILID + '\'' + 
			",iD = '" + iD + '\'' + 
			",lOCATION_NAME = '" + lOCATIONNAME + '\'' + 
			",lATITUDE = '" + lATITUDE + '\'' + 
			",nAME = '" + nAME + '\'' + 
			"}";
		}
}
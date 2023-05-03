package com.vugido.foodhub.v_c.models.login;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("CONTACT")
	private String cONTACT;

	@JsonProperty("ACTIVE_STATE")
	private int aCTIVESTATE;

	@JsonProperty("BUSINESS_NAME")
	private String bUSINESSNAME;

	@JsonProperty("OFFER")
	private int oFFER;

	@JsonProperty("LONGITUDE")
	private String lONGITUDE;

	@JsonProperty("MIN_PRICE")
	private int mINPRICE;

	@JsonProperty("LOGO")
	private String lOGO;

	@JsonProperty("NAME")
	private String nAME;

	@JsonProperty("TYPE_ID")
	private int tYPEID;

	@JsonProperty("PASSWORD")
	private String pASSWORD;

	@JsonProperty("RATING")
	private String rATING;

	@JsonProperty("CREATED_ON")
	private String cREATEDON;

	@JsonProperty("MAIN")
	private int mAIN;

	@JsonProperty("EMAIL_ID")
	private String eMAILID;

	@JsonProperty("ID")
	private int iD;

	@JsonProperty("LOCATION_NAME")
	private String lOCATIONNAME;

	@JsonProperty("LATITUDE")
	private String lATITUDE;

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

	public void setBUSINESSNAME(String bUSINESSNAME){
		this.bUSINESSNAME = bUSINESSNAME;
	}

	public String getBUSINESSNAME(){
		return bUSINESSNAME;
	}

	public void setOFFER(int oFFER){
		this.oFFER = oFFER;
	}

	public int getOFFER(){
		return oFFER;
	}

	public void setLONGITUDE(String lONGITUDE){
		this.lONGITUDE = lONGITUDE;
	}

	public String getLONGITUDE(){
		return lONGITUDE;
	}

	public void setMINPRICE(int mINPRICE){
		this.mINPRICE = mINPRICE;
	}

	public int getMINPRICE(){
		return mINPRICE;
	}

	public void setLOGO(String lOGO){
		this.lOGO = lOGO;
	}

	public String getLOGO(){
		return lOGO;
	}

	public void setNAME(String nAME){
		this.nAME = nAME;
	}

	public String getNAME(){
		return nAME;
	}

	public void setTYPEID(int tYPEID){
		this.tYPEID = tYPEID;
	}

	public int getTYPEID(){
		return tYPEID;
	}

	public void setPASSWORD(String pASSWORD){
		this.pASSWORD = pASSWORD;
	}

	public String getPASSWORD(){
		return pASSWORD;
	}

	public void setRATING(String rATING){
		this.rATING = rATING;
	}

	public String getRATING(){
		return rATING;
	}

	public void setCREATEDON(String cREATEDON){
		this.cREATEDON = cREATEDON;
	}

	public String getCREATEDON(){
		return cREATEDON;
	}

	public void setMAIN(int mAIN){
		this.mAIN = mAIN;
	}

	public int getMAIN(){
		return mAIN;
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

	@Override
 	public String toString(){
		return 
			"DATAItem{" + 
			"cONTACT = '" + cONTACT + '\'' + 
			",aCTIVE_STATE = '" + aCTIVESTATE + '\'' + 
			",bUSINESS_NAME = '" + bUSINESSNAME + '\'' + 
			",oFFER = '" + oFFER + '\'' + 
			",lONGITUDE = '" + lONGITUDE + '\'' + 
			",mIN_PRICE = '" + mINPRICE + '\'' + 
			",lOGO = '" + lOGO + '\'' + 
			",nAME = '" + nAME + '\'' + 
			",tYPE_ID = '" + tYPEID + '\'' + 
			",pASSWORD = '" + pASSWORD + '\'' + 
			",rATING = '" + rATING + '\'' + 
			",cREATED_ON = '" + cREATEDON + '\'' + 
			",mAIN = '" + mAIN + '\'' + 
			",eMAIL_ID = '" + eMAILID + '\'' + 
			",iD = '" + iD + '\'' + 
			",lOCATION_NAME = '" + lOCATIONNAME + '\'' + 
			",lATITUDE = '" + lATITUDE + '\'' + 
			"}";
		}
}
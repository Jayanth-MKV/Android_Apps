package com.vugido.homeservices.models.near_page;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NEARSERVICESItem{

	@JsonProperty("TYPE_ID")
	private int tYPEID;

	@JsonProperty("CONTACT")
	private String cONTACT;

	@JsonProperty("SERVICES")
	private String sERVICES;

	@JsonProperty("BUSINESS_NAME")
	private String bUSINESSNAME;

	@JsonProperty("LONGITUDE")
	private String lONGITUDE;

	@JsonProperty("LOGO")
	private String lOGO;

	@JsonProperty("DESCRIBER")
	private String dESCRIBER;

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

	@JsonProperty("RA")
	private String rA;

	public String getrA() {
		return rA;
	}

	public void setrA(String rA) {
		this.rA = rA;
	}

	public void setTYPEID(int tYPEID){
		this.tYPEID = tYPEID;
	}

	public int getTYPEID(){
		return tYPEID;
	}

	public void setCONTACT(String cONTACT){
		this.cONTACT = cONTACT;
	}

	public String getCONTACT(){
		return cONTACT;
	}

	public void setSERVICES(String sERVICES){
		this.sERVICES = sERVICES;
	}

	public String getSERVICES(){
		return sERVICES;
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

	public void setDESCRIBER(String dESCRIBER){
		this.dESCRIBER = dESCRIBER;
	}

	public String getDESCRIBER(){
		return dESCRIBER;
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
			"NEARSERVICESItem{" + 
			"tYPE_ID = '" + tYPEID + '\'' + 
			",cONTACT = '" + cONTACT + '\'' + 
			",sERVICES = '" + sERVICES + '\'' + 
			",bUSINESS_NAME = '" + bUSINESSNAME + '\'' + 
			",lONGITUDE = '" + lONGITUDE + '\'' + 
			",lOGO = '" + lOGO + '\'' + 
			",dESCRIBER = '" + dESCRIBER + '\'' + 
			",eMAIL_ID = '" + eMAILID + '\'' + 
			",iD = '" + iD + '\'' + 
			",lOCATION_NAME = '" + lOCATIONNAME + '\'' + 
			",lATITUDE = '" + lATITUDE + '\'' + 
			",nAME = '" + nAME + '\'' +
			",rA = '" + rA + '\'' +
					"}";
		}
}
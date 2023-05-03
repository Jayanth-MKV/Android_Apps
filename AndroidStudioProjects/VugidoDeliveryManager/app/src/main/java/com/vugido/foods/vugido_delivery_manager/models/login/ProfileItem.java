package com.vugido.foods.vugido_delivery_manager.models.login;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfileItem{

	@JsonProperty("IMAGE")
	private String iMAGE;

	@JsonProperty("PHONE")
	private String pHONE;

	@JsonProperty("JOINED_ON")
	private String jOINEDON;

	@JsonProperty("ADDRESS")
	private String aDDRESS;

	@JsonProperty("EMAIL_ID")
	private String eMAILID;

	@JsonProperty("EDUCATION")
	private String eDUCATION;

	@JsonProperty("DID")
	private int dID;

	@JsonProperty("NAME")
	private String nAME;

	@JsonProperty("AGE")
	private int aGE;

	public void setIMAGE(String iMAGE){
		this.iMAGE = iMAGE;
	}

	public String getIMAGE(){
		return iMAGE;
	}

	public void setPHONE(String pHONE){
		this.pHONE = pHONE;
	}

	public String getPHONE(){
		return pHONE;
	}

	public void setJOINEDON(String jOINEDON){
		this.jOINEDON = jOINEDON;
	}

	public String getJOINEDON(){
		return jOINEDON;
	}

	public void setADDRESS(String aDDRESS){
		this.aDDRESS = aDDRESS;
	}

	public String getADDRESS(){
		return aDDRESS;
	}

	public void setEMAILID(String eMAILID){
		this.eMAILID = eMAILID;
	}

	public String getEMAILID(){
		return eMAILID;
	}

	public void setEDUCATION(String eDUCATION){
		this.eDUCATION = eDUCATION;
	}

	public String getEDUCATION(){
		return eDUCATION;
	}

	public void setDID(int dID){
		this.dID = dID;
	}

	public int getDID(){
		return dID;
	}

	public void setNAME(String nAME){
		this.nAME = nAME;
	}

	public String getNAME(){
		return nAME;
	}

	public void setAGE(int aGE){
		this.aGE = aGE;
	}

	public int getAGE(){
		return aGE;
	}

	@Override
 	public String toString(){
		return 
			"ProfileItem{" + 
			"iMAGE = '" + iMAGE + '\'' + 
			",pHONE = '" + pHONE + '\'' + 
			",jOINED_ON = '" + jOINEDON + '\'' + 
			",aDDRESS = '" + aDDRESS + '\'' + 
			",eMAIL_ID = '" + eMAILID + '\'' + 
			",eDUCATION = '" + eDUCATION + '\'' + 
			",dID = '" + dID + '\'' + 
			",nAME = '" + nAME + '\'' + 
			",aGE = '" + aGE + '\'' + 
			"}";
		}
}
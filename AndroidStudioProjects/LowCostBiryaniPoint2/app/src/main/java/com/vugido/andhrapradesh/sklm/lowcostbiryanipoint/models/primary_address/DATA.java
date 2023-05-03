package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.primary_address;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATA{

	@JsonProperty("ZIP")
	private int zIP;

	@JsonProperty("DOOR_NO")
	private String dOORNO;

	@JsonProperty("AREA_NAME")
	private String aREANAME;

	@JsonProperty("FULL_NAME")
	private String fULLNAME;

	@JsonProperty("COUNTRY_STATE")
	private String cOUNTRYSTATE;

	@JsonProperty("AID")
	private int aID;

	@JsonProperty("ACTIVE_PHONE")
	private String aCTIVEPHONE;

	public void setZIP(int zIP){
		this.zIP = zIP;
	}

	public int getZIP(){
		return zIP;
	}

	public void setDOORNO(String dOORNO){
		this.dOORNO = dOORNO;
	}

	public String getDOORNO(){
		return dOORNO;
	}

	public void setAREANAME(String aREANAME){
		this.aREANAME = aREANAME;
	}

	public String getAREANAME(){
		return aREANAME;
	}

	public void setFULLNAME(String fULLNAME){
		this.fULLNAME = fULLNAME;
	}

	public String getFULLNAME(){
		return fULLNAME;
	}

	public void setCOUNTRYSTATE(String cOUNTRYSTATE){
		this.cOUNTRYSTATE = cOUNTRYSTATE;
	}

	public String getCOUNTRYSTATE(){
		return cOUNTRYSTATE;
	}

	public void setAID(int aID){
		this.aID = aID;
	}

	public int getAID(){
		return aID;
	}

	public void setACTIVEPHONE(String aCTIVEPHONE){
		this.aCTIVEPHONE = aCTIVEPHONE;
	}

	public String getACTIVEPHONE(){
		return aCTIVEPHONE;
	}

	@Override
 	public String toString(){
		return 
			"DATA{" + 
			"zIP = '" + zIP + '\'' + 
			",dOOR_NO = '" + dOORNO + '\'' + 
			",aREA_NAME = '" + aREANAME + '\'' + 
			",fULL_NAME = '" + fULLNAME + '\'' + 
			",cOUNTRY_STATE = '" + cOUNTRYSTATE + '\'' + 
			",aID = '" + aID + '\'' + 
			",aCTIVE_PHONE = '" + aCTIVEPHONE + '\'' + 
			"}";
		}
}
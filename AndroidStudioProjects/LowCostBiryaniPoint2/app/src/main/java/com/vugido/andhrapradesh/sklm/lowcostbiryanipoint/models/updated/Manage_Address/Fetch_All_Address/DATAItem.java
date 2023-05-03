package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.updated.Manage_Address.Fetch_All_Address;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("DOOR_NO")
	private String dOORNO;

	@JsonProperty("IS_PRIMARY")
	private int iSPRIMARY;

	@JsonProperty("AREA_NAME")
	private String aREANAME;

	@JsonProperty("ZIPCODE")
	private int zIPCODE;

	@JsonProperty("COUNTRY_STATE")
	private String cOUNTRYSTATE;


	@JsonProperty("AID")
	private int aID;

	@JsonProperty("FULL_NAME")
	private String fIRSTNAME;

	@JsonProperty("ACTIVE_PHONE")
	private String aCTIVEPHONE;

	public void setDOORNO(String dOORNO){
		this.dOORNO = dOORNO;
	}

	public String getDOORNO(){
		return dOORNO;
	}

	public void setISPRIMARY(int iSPRIMARY){
		this.iSPRIMARY = iSPRIMARY;
	}

	public int getISPRIMARY(){
		return iSPRIMARY;
	}

	public void setAREANAME(String aREANAME){
		this.aREANAME = aREANAME;
	}

	public String getAREANAME(){
		return aREANAME;
	}

	public void setZIPCODE(int zIPCODE){
		this.zIPCODE = zIPCODE;
	}

	public int getZIPCODE(){
		return zIPCODE;
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

	public void setFIRSTNAME(String fIRSTNAME){
		this.fIRSTNAME = fIRSTNAME;
	}

	public String getFIRSTNAME(){
		return fIRSTNAME;
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
			"DATAItem{" + 
			"dOOR_NO = '" + dOORNO + '\'' + 
			",iS_PRIMARY = '" + iSPRIMARY + '\'' + 
			",aREA_NAME = '" + aREANAME + '\'' + 
			",zIPCODE = '" + zIPCODE + '\'' + 
			",cOUNTRY_STATE = '" + cOUNTRYSTATE + '\'' +
			",aID = '" + aID + '\'' + 
			",fIRST_NAME = '" + fIRSTNAME + '\'' + 
			",aCTIVE_PHONE = '" + aCTIVEPHONE + '\'' + 
			"}";
		}
}
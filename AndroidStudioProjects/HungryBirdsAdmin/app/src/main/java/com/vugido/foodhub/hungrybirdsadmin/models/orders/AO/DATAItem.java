package com.vugido.foodhub.hungrybirdsadmin.models.orders.AO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("LONGITUDE")
	private String lONGITUDE;

	@JsonProperty("OID")
	private int oID;

	@JsonProperty("ITEM_COUNT")
	private int iTEMCOUNT;

	@JsonProperty("DATED")
	private String dATED;

	@JsonProperty("PAY_MODE")
	private int pAYMODE;

	@JsonProperty("UID")
	private int uID;

	@JsonProperty("STATUS")
	private int sTATUS;

	@JsonProperty("DELIVERY_CHARGE")
	private int dELIVERYCHARGE;

	@JsonProperty("NOTE")
	private String nOTE;

	@JsonProperty("ADDRESS")
	private String aDDRESS;

	@JsonProperty("ITEM_PRICE")
	private int iTEMPRICE;

	@JsonProperty("LATITUDE")
	private String lATITUDE;

	@JsonProperty("ACTIVE_PHONE")
	private String aCTIVEPHONE;

	public void setLONGITUDE(String lONGITUDE){
		this.lONGITUDE = lONGITUDE;
	}

	public String getLONGITUDE(){
		return lONGITUDE;
	}

	public void setOID(int oID){
		this.oID = oID;
	}

	public int getOID(){
		return oID;
	}

	public void setITEMCOUNT(int iTEMCOUNT){
		this.iTEMCOUNT = iTEMCOUNT;
	}

	public int getITEMCOUNT(){
		return iTEMCOUNT;
	}

	public void setDATED(String dATED){
		this.dATED = dATED;
	}

	public String getDATED(){
		return dATED;
	}

	public void setPAYMODE(int pAYMODE){
		this.pAYMODE = pAYMODE;
	}

	public int getPAYMODE(){
		return pAYMODE;
	}

	public void setUID(int uID){
		this.uID = uID;
	}

	public int getUID(){
		return uID;
	}

	public void setSTATUS(int sTATUS){
		this.sTATUS = sTATUS;
	}

	public int getSTATUS(){
		return sTATUS;
	}

	public void setDELIVERYCHARGE(int dELIVERYCHARGE){
		this.dELIVERYCHARGE = dELIVERYCHARGE;
	}

	public int getDELIVERYCHARGE(){
		return dELIVERYCHARGE;
	}

	public void setNOTE(String nOTE){
		this.nOTE = nOTE;
	}

	public String getNOTE(){
		return nOTE;
	}

	public void setADDRESS(String aDDRESS){
		this.aDDRESS = aDDRESS;
	}

	public String getADDRESS(){
		return aDDRESS;
	}

	public void setITEMPRICE(int iTEMPRICE){
		this.iTEMPRICE = iTEMPRICE;
	}

	public int getITEMPRICE(){
		return iTEMPRICE;
	}

	public void setLATITUDE(String lATITUDE){
		this.lATITUDE = lATITUDE;
	}

	public String getLATITUDE(){
		return lATITUDE;
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
			"lONGITUDE = '" + lONGITUDE + '\'' + 
			",oID = '" + oID + '\'' + 
			",iTEM_COUNT = '" + iTEMCOUNT + '\'' + 
			",dATED = '" + dATED + '\'' + 
			",pAY_MODE = '" + pAYMODE + '\'' + 
			",uID = '" + uID + '\'' + 
			",sTATUS = '" + sTATUS + '\'' + 
			",dELIVERY_CHARGE = '" + dELIVERYCHARGE + '\'' + 
			",nOTE = '" + nOTE + '\'' + 
			",aDDRESS = '" + aDDRESS + '\'' + 
			",iTEM_PRICE = '" + iTEMPRICE + '\'' + 
			",lATITUDE = '" + lATITUDE + '\'' + 
			",aCTIVE_PHONE = '" + aCTIVEPHONE + '\'' + 
			"}";
		}
}
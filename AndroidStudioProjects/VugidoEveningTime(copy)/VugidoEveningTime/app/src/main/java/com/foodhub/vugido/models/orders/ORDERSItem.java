package com.foodhub.vugido.models.orders;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ORDERSItem{

	@JsonProperty("ITEMS_PRICE")
	private int iTEMSPRICE;

	@JsonProperty("LM")
	private String lM;

	@JsonProperty("BUSINESS_NAME")
	private String bUSINESSNAME;

	@JsonProperty("LONGITUDE")
	private String lONGITUDE;

	@JsonProperty("LOGO")
	private String lOGO;

	@JsonProperty("OID")
	private int oID;

	@JsonProperty("ITEM_COUNT")
	private int iTEMCOUNT;

	@JsonProperty("DATED")
	private String dATED;

	@JsonProperty("B_LATITUDE")
	private String bLATITUDE;

	@JsonProperty("B_LONGITUDE")
	private String bLONGITUDE;

	@JsonProperty("STATUS")
	private int sTATUS;

	@JsonProperty("RATING")
	private String rATING;

	@JsonProperty("PRODUCTS")
	private List<PRODUCTSItem> pRODUCTS;

	@JsonProperty("ADDRESS")
	private String aDDRESS;

	@JsonProperty("REVIEW")
	private Object rEVIEW;

	@JsonProperty("LATITUDE")
	private String lATITUDE;

	@JsonProperty("DC")
	private int dC;

	@JsonProperty("B_LOCATION_NAME")
	private String bLOCATIONNAME;

	public void setITEMSPRICE(int iTEMSPRICE){
		this.iTEMSPRICE = iTEMSPRICE;
	}

	public int getITEMSPRICE(){
		return iTEMSPRICE;
	}

	public void setLM(String lM){
		this.lM = lM;
	}

	public String getLM(){
		return lM;
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

	public void setBLATITUDE(String bLATITUDE){
		this.bLATITUDE = bLATITUDE;
	}

	public String getBLATITUDE(){
		return bLATITUDE;
	}

	public void setBLONGITUDE(String bLONGITUDE){
		this.bLONGITUDE = bLONGITUDE;
	}

	public String getBLONGITUDE(){
		return bLONGITUDE;
	}

	public void setSTATUS(int sTATUS){
		this.sTATUS = sTATUS;
	}

	public int getSTATUS(){
		return sTATUS;
	}

	public void setRATING(String rATING){
		this.rATING = rATING;
	}

	public String getRATING(){
		return rATING;
	}

	public void setPRODUCTS(List<PRODUCTSItem> pRODUCTS){
		this.pRODUCTS = pRODUCTS;
	}

	public List<PRODUCTSItem> getPRODUCTS(){
		return pRODUCTS;
	}

	public void setADDRESS(String aDDRESS){
		this.aDDRESS = aDDRESS;
	}

	public String getADDRESS(){
		return aDDRESS;
	}

	public void setREVIEW(Object rEVIEW){
		this.rEVIEW = rEVIEW;
	}

	public Object getREVIEW(){
		return rEVIEW;
	}

	public void setLATITUDE(String lATITUDE){
		this.lATITUDE = lATITUDE;
	}

	public String getLATITUDE(){
		return lATITUDE;
	}

	public void setDC(int dC){
		this.dC = dC;
	}

	public int getDC(){
		return dC;
	}

	public void setBLOCATIONNAME(String bLOCATIONNAME){
		this.bLOCATIONNAME = bLOCATIONNAME;
	}

	public String getBLOCATIONNAME(){
		return bLOCATIONNAME;
	}

	@Override
 	public String toString(){
		return 
			"ORDERSItem{" + 
			"iTEMS_PRICE = '" + iTEMSPRICE + '\'' + 
			",lM = '" + lM + '\'' + 
			",bUSINESS_NAME = '" + bUSINESSNAME + '\'' + 
			",lONGITUDE = '" + lONGITUDE + '\'' + 
			",lOGO = '" + lOGO + '\'' + 
			",oID = '" + oID + '\'' + 
			",iTEM_COUNT = '" + iTEMCOUNT + '\'' + 
			",dATED = '" + dATED + '\'' + 
			",b_LATITUDE = '" + bLATITUDE + '\'' + 
			",b_LONGITUDE = '" + bLONGITUDE + '\'' + 
			",sTATUS = '" + sTATUS + '\'' + 
			",rATING = '" + rATING + '\'' + 
			",pRODUCTS = '" + pRODUCTS + '\'' + 
			",aDDRESS = '" + aDDRESS + '\'' + 
			",rEVIEW = '" + rEVIEW + '\'' + 
			",lATITUDE = '" + lATITUDE + '\'' + 
			",dC = '" + dC + '\'' + 
			",b_LOCATION_NAME = '" + bLOCATIONNAME + '\'' + 
			"}";
		}
}
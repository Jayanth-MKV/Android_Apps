package com.vugido.online_groceries.models.orders.all_orders;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ORDERSItem{

	@JsonProperty("ITEMS_PRICE")
	private int iTEMSPRICE;

	@JsonProperty("LM")
	private String lM;

	@JsonProperty("STATUS")
	private int sTATUS;

	@JsonProperty("PRODUCTS")
	private List<PRODUCTSItem> pRODUCTS;

	@JsonProperty("LONGITUDE")
	private String lONGITUDE;

	@JsonProperty("ADDRESS")
	private String aDDRESS;

	@JsonProperty("OID")
	private int oID;

	@JsonProperty("ITEM_COUNT")
	private int iTEMCOUNT;

	@JsonProperty("DATED")
	private String dATED;

	@JsonProperty("LATITUDE")
	private String lATITUDE;

	@JsonProperty("DC")
	private int dC;

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

	public void setSTATUS(int sTATUS){
		this.sTATUS = sTATUS;
	}

	public int getSTATUS(){
		return sTATUS;
	}

	public void setPRODUCTS(List<PRODUCTSItem> pRODUCTS){
		this.pRODUCTS = pRODUCTS;
	}

	public List<PRODUCTSItem> getPRODUCTS(){
		return pRODUCTS;
	}

	public void setLONGITUDE(String lONGITUDE){
		this.lONGITUDE = lONGITUDE;
	}

	public String getLONGITUDE(){
		return lONGITUDE;
	}

	public void setADDRESS(String aDDRESS){
		this.aDDRESS = aDDRESS;
	}

	public String getADDRESS(){
		return aDDRESS;
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

	@Override
 	public String toString(){
		return 
			"ORDERSItem{" + 
			"iTEMS_PRICE = '" + iTEMSPRICE + '\'' + 
			",lM = '" + lM + '\'' + 
			",sTATUS = '" + sTATUS + '\'' + 
			",pRODUCTS = '" + pRODUCTS + '\'' + 
			",lONGITUDE = '" + lONGITUDE + '\'' + 
			",aDDRESS = '" + aDDRESS + '\'' + 
			",oID = '" + oID + '\'' + 
			",iTEM_COUNT = '" + iTEMCOUNT + '\'' + 
			",dATED = '" + dATED + '\'' + 
			",lATITUDE = '" + lATITUDE + '\'' + 
			",dC = '" + dC + '\'' + 
			"}";
		}
}
package com.vugido.foodhub.vdexecutives.models.orders.pending_orders;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("UID")
	private int uID;

	@JsonProperty("STATUS")
	private int sTATUS;

	@JsonProperty("DELIVERY_CHARGE")
	private int dELIVERYCHARGE;

	@JsonProperty("DELIVER_DATE")
	private String dELIVERDATE;

	@JsonProperty("TIME")
	private String tIME;

	@JsonProperty("OID")
	private int oID;

	@JsonProperty("ITEM_COUNT")
	private int iTEMCOUNT;

	@JsonProperty("DATED")
	private String dATED;

	@JsonProperty("AID")
	private AID aID;

	@JsonProperty("ITEM_PRICE")
	private int iTEMPRICE;

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

	public void setDELIVERDATE(String dELIVERDATE){
		this.dELIVERDATE = dELIVERDATE;
	}

	public String getDELIVERDATE(){
		return dELIVERDATE;
	}

	public void setTIME(String tIME){
		this.tIME = tIME;
	}

	public String getTIME(){
		return tIME;
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

	public void setAID(AID aID){
		this.aID = aID;
	}

	public AID getAID(){
		return aID;
	}

	public void setITEMPRICE(int iTEMPRICE){
		this.iTEMPRICE = iTEMPRICE;
	}

	public int getITEMPRICE(){
		return iTEMPRICE;
	}

	@Override
 	public String toString(){
		return 
			"DATAItem{" + 
			"uID = '" + uID + '\'' + 
			",sTATUS = '" + sTATUS + '\'' + 
			",dELIVERY_CHARGE = '" + dELIVERYCHARGE + '\'' + 
			",dELIVER_DATE = '" + dELIVERDATE + '\'' + 
			",tIME = '" + tIME + '\'' + 
			",oID = '" + oID + '\'' + 
			",iTEM_COUNT = '" + iTEMCOUNT + '\'' + 
			",dATED = '" + dATED + '\'' + 
			",aID = '" + aID + '\'' + 
			",iTEM_PRICE = '" + iTEMPRICE + '\'' + 
			"}";
		}
}
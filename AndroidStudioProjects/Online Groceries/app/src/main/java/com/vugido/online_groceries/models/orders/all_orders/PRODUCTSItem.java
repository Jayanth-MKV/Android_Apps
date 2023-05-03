package com.vugido.online_groceries.models.orders.all_orders;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PRODUCTSItem{

	@JsonProperty("ITEMS_COUNT")
	private int iTEMSCOUNT;

	@JsonProperty("PRICE")
	private String pRICE;

	@JsonProperty("QUANTITY")
	private String qUANTITY;

	@JsonProperty("OFFER")
	private String oFFER;

	@JsonProperty("PID")
	private int pID;

	@JsonProperty("OID")
	private int oID;

	@JsonProperty("IN_STOCK")
	private int iNSTOCK;

	@JsonProperty("NAME")
	private String nAME;

	@JsonProperty("OFFER_ENABLED")
	private int oFFERENABLED;

	@JsonProperty("IMAGE")
	private String iMAGE;

	@JsonProperty("DESCRIPTION")
	private String dESCRIPTION;

	@JsonProperty("BID")
	private int bID;

	@JsonProperty("HOME_ENABLED")
	private int hOMEENABLED;

	@JsonProperty("$ID")
	private int iD;

	@JsonProperty("CID")
	private int cID;

	public void setITEMSCOUNT(int iTEMSCOUNT){
		this.iTEMSCOUNT = iTEMSCOUNT;
	}

	public int getITEMSCOUNT(){
		return iTEMSCOUNT;
	}

	public void setPRICE(String pRICE){
		this.pRICE = pRICE;
	}

	public String getPRICE(){
		return pRICE;
	}

	public void setQUANTITY(String qUANTITY){
		this.qUANTITY = qUANTITY;
	}

	public String getQUANTITY(){
		return qUANTITY;
	}

	public void setOFFER(String oFFER){
		this.oFFER = oFFER;
	}

	public String getOFFER(){
		return oFFER;
	}

	public void setPID(int pID){
		this.pID = pID;
	}

	public int getPID(){
		return pID;
	}

	public void setOID(int oID){
		this.oID = oID;
	}

	public int getOID(){
		return oID;
	}

	public void setINSTOCK(int iNSTOCK){
		this.iNSTOCK = iNSTOCK;
	}

	public int getINSTOCK(){
		return iNSTOCK;
	}

	public void setNAME(String nAME){
		this.nAME = nAME;
	}

	public String getNAME(){
		return nAME;
	}

	public void setOFFERENABLED(int oFFERENABLED){
		this.oFFERENABLED = oFFERENABLED;
	}

	public int getOFFERENABLED(){
		return oFFERENABLED;
	}

	public void setIMAGE(String iMAGE){
		this.iMAGE = iMAGE;
	}

	public String getIMAGE(){
		return iMAGE;
	}

	public void setDESCRIPTION(String dESCRIPTION){
		this.dESCRIPTION = dESCRIPTION;
	}

	public String getDESCRIPTION(){
		return dESCRIPTION;
	}

	public void setBID(int bID){
		this.bID = bID;
	}

	public int getBID(){
		return bID;
	}

	public void setHOMEENABLED(int hOMEENABLED){
		this.hOMEENABLED = hOMEENABLED;
	}

	public int getHOMEENABLED(){
		return hOMEENABLED;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setCID(int cID){
		this.cID = cID;
	}

	public int getCID(){
		return cID;
	}

	@Override
 	public String toString(){
		return 
			"PRODUCTSItem{" + 
			"iTEMS_COUNT = '" + iTEMSCOUNT + '\'' + 
			",pRICE = '" + pRICE + '\'' + 
			",qUANTITY = '" + qUANTITY + '\'' + 
			",oFFER = '" + oFFER + '\'' + 
			",pID = '" + pID + '\'' + 
			",oID = '" + oID + '\'' + 
			",iN_STOCK = '" + iNSTOCK + '\'' + 
			",nAME = '" + nAME + '\'' + 
			",oFFER_ENABLED = '" + oFFERENABLED + '\'' + 
			",iMAGE = '" + iMAGE + '\'' + 
			",dESCRIPTION = '" + dESCRIPTION + '\'' + 
			",bID = '" + bID + '\'' + 
			",hOME_ENABLED = '" + hOMEENABLED + '\'' + 
			",$ID = '" + iD + '\'' + 
			",cID = '" + cID + '\'' + 
			"}";
		}
}
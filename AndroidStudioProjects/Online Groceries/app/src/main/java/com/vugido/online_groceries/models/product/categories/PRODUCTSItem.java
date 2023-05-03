package com.vugido.online_groceries.models.product.categories;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PRODUCTSItem{

	@JsonProperty("PRICE")
	private String pRICE;

	@JsonProperty("QUANTITY")
	private String qUANTITY;

	@JsonProperty("OFFER")
	private String oFFER;

	@JsonProperty("CART_COUNT")
	private int cARTCOUNT;

	@JsonProperty("IN_STOCK")
	private int iNSTOCK;

	@JsonProperty("CART_ID")
	private int cARTID;

	@JsonProperty("OFFER_ENABLED")
	private int oFFERENABLED;

	@JsonProperty("IMAGE")
	private String iMAGE;

	@JsonProperty("DESCRIPTION")
	private String dESCRIPTION;

	@JsonProperty("TITLE")
	private String tITLE;

	@JsonProperty("ID")
	private int iD;

	@JsonProperty("BID")
	private int bID;

	@JsonProperty("HOME_ENABLED")
	private int hOMEENABLED;

	@JsonProperty("CID")
	private int cID;

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

	public void setCARTCOUNT(int cARTCOUNT){
		this.cARTCOUNT = cARTCOUNT;
	}

	public int getCARTCOUNT(){
		return cARTCOUNT;
	}

	public void setINSTOCK(int iNSTOCK){
		this.iNSTOCK = iNSTOCK;
	}

	public int getINSTOCK(){
		return iNSTOCK;
	}

	public void setCARTID(int cARTID){
		this.cARTID = cARTID;
	}

	public int getCARTID(){
		return cARTID;
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

	public void setTITLE(String tITLE){
		this.tITLE = tITLE;
	}

	public String getTITLE(){
		return tITLE;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
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
			"pRICE = '" + pRICE + '\'' + 
			",qUANTITY = '" + qUANTITY + '\'' + 
			",oFFER = '" + oFFER + '\'' + 
			",cART_COUNT = '" + cARTCOUNT + '\'' + 
			",iN_STOCK = '" + iNSTOCK + '\'' + 
			",cART_ID = '" + cARTID + '\'' + 
			",oFFER_ENABLED = '" + oFFERENABLED + '\'' + 
			",iMAGE = '" + iMAGE + '\'' + 
			",dESCRIPTION = '" + dESCRIPTION + '\'' + 
			",tITLE = '" + tITLE + '\'' + 
			",iD = '" + iD + '\'' + 
			",bID = '" + bID + '\'' + 
			",hOME_ENABLED = '" + hOMEENABLED + '\'' + 
			",cID = '" + cID + '\'' + 
			"}";
		}
}
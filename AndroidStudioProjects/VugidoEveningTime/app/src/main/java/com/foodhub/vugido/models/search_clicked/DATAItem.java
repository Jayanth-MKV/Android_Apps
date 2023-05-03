package com.foodhub.vugido.models.search_clicked;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("PRICE")
	private int pRICE;

	@JsonProperty("OFFER_LIMIT")
	private int oFFERLIMIT;

	@JsonProperty("QUANTITY")
	private String qUANTITY;

	@JsonProperty("OFFER")
	private int oFFER;

	@JsonProperty("IN_STOCK")
	private int iNSTOCK;

	@JsonProperty("CART_LIMIT")
	private int cARTLIMIT;

	@JsonProperty("TID")
	private int tID;

	@JsonProperty("IMAGE")
	private String iMAGE;

	@JsonProperty("SUB_CAT_ID")
	private int sUBCATID;

	@JsonProperty("DESCRIPTION")
	private String dESCRIPTION;

	@JsonProperty("E_NAME")
	private String eNAME;

	@JsonProperty("VEG")
	private int vEG;

	@JsonProperty("ID")
	private int iD;

	@JsonProperty("CID")
	private String cID;

	public void setPRICE(int pRICE){
		this.pRICE = pRICE;
	}

	public int getPRICE(){
		return pRICE;
	}

	public void setOFFERLIMIT(int oFFERLIMIT){
		this.oFFERLIMIT = oFFERLIMIT;
	}

	public int getOFFERLIMIT(){
		return oFFERLIMIT;
	}

	public void setQUANTITY(String qUANTITY){
		this.qUANTITY = qUANTITY;
	}

	public String getQUANTITY(){
		return qUANTITY;
	}

	public void setOFFER(int oFFER){
		this.oFFER = oFFER;
	}

	public int getOFFER(){
		return oFFER;
	}

	public void setINSTOCK(int iNSTOCK){
		this.iNSTOCK = iNSTOCK;
	}

	public int getINSTOCK(){
		return iNSTOCK;
	}

	public void setCARTLIMIT(int cARTLIMIT){
		this.cARTLIMIT = cARTLIMIT;
	}

	public int getCARTLIMIT(){
		return cARTLIMIT;
	}

	public void setTID(int tID){
		this.tID = tID;
	}

	public int getTID(){
		return tID;
	}

	public void setIMAGE(String iMAGE){
		this.iMAGE = iMAGE;
	}

	public String getIMAGE(){
		return iMAGE;
	}

	public void setSUBCATID(int sUBCATID){
		this.sUBCATID = sUBCATID;
	}

	public int getSUBCATID(){
		return sUBCATID;
	}

	public void setDESCRIPTION(String dESCRIPTION){
		this.dESCRIPTION = dESCRIPTION;
	}

	public String getDESCRIPTION(){
		return dESCRIPTION;
	}

	public void setENAME(String eNAME){
		this.eNAME = eNAME;
	}

	public String getENAME(){
		return eNAME;
	}

	public void setVEG(int vEG){
		this.vEG = vEG;
	}

	public int getVEG(){
		return vEG;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setCID(String cID){
		this.cID = cID;
	}

	public String getCID(){
		return cID;
	}

	@Override
 	public String toString(){
		return 
			"DATAItem{" + 
			"pRICE = '" + pRICE + '\'' + 
			",oFFER_LIMIT = '" + oFFERLIMIT + '\'' + 
			",qUANTITY = '" + qUANTITY + '\'' + 
			",oFFER = '" + oFFER + '\'' + 
			",iN_STOCK = '" + iNSTOCK + '\'' + 
			",cART_LIMIT = '" + cARTLIMIT + '\'' + 
			",tID = '" + tID + '\'' + 
			",iMAGE = '" + iMAGE + '\'' + 
			",sUB_CAT_ID = '" + sUBCATID + '\'' + 
			",dESCRIPTION = '" + dESCRIPTION + '\'' + 
			",e_NAME = '" + eNAME + '\'' + 
			",vEG = '" + vEG + '\'' + 
			",iD = '" + iD + '\'' + 
			",cID = '" + cID + '\'' + 
			"}";
		}
}
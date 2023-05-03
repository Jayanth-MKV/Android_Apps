package com.vugido.foodhub.hungrybirdsadmin.models.orders.ordered_items;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("UID")
	private int uID;

	@JsonProperty("IMAGE")
	private String iMAGE;

	@JsonProperty("PRICE")
	private int pRICE;

	@JsonProperty("OFFER_LIMIT")
	private int oFFERLIMIT;

	@JsonProperty("QUANTITY")
	private int qUANTITY;

	@JsonProperty("SUB_CAT_ID")
	private int sUBCATID;

	@JsonProperty("DESCRIPTION")
	private String dESCRIPTION;

	@JsonProperty("OFFER")
	private int oFFER;

	@JsonProperty("E_NAME")
	private String eNAME;

	@JsonProperty("PID")
	private int pID;

	@JsonProperty("OID")
	private int oID;

	@JsonProperty("IN_STOCK")
	private int iNSTOCK;

	public void setUID(int uID){
		this.uID = uID;
	}

	public int getUID(){
		return uID;
	}

	public void setIMAGE(String iMAGE){
		this.iMAGE = iMAGE;
	}

	public String getIMAGE(){
		return iMAGE;
	}

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

	public void setQUANTITY(int qUANTITY){
		this.qUANTITY = qUANTITY;
	}

	public int getQUANTITY(){
		return qUANTITY;
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

	public void setOFFER(int oFFER){
		this.oFFER = oFFER;
	}

	public int getOFFER(){
		return oFFER;
	}

	public void setENAME(String eNAME){
		this.eNAME = eNAME;
	}

	public String getENAME(){
		return eNAME;
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

	@Override
 	public String toString(){
		return 
			"DATAItem{" + 
			"uID = '" + uID + '\'' + 
			",iMAGE = '" + iMAGE + '\'' + 
			",pRICE = '" + pRICE + '\'' + 
			",oFFER_LIMIT = '" + oFFERLIMIT + '\'' + 
			",qUANTITY = '" + qUANTITY + '\'' + 
			",sUB_CAT_ID = '" + sUBCATID + '\'' + 
			",dESCRIPTION = '" + dESCRIPTION + '\'' + 
			",oFFER = '" + oFFER + '\'' + 
			",e_NAME = '" + eNAME + '\'' + 
			",pID = '" + pID + '\'' + 
			",oID = '" + oID + '\'' + 
			",iN_STOCK = '" + iNSTOCK + '\'' + 
			"}";
		}
}
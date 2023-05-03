package com.vugido_business_panel.models.newOrders;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("T_NAME")
	private String tNAME;

	@JsonProperty("PRICE")
	private int pRICE;

	@JsonProperty("OFFER_LIMIT")
	private int oFFERLIMIT;

	@JsonProperty("QUANTITY")
	private int qUANTITY;

	@JsonProperty("OFFER")
	private int oFFER;

	@JsonProperty("PID")
	private int pID;

	@JsonProperty("OID")
	private int oID;

	@JsonProperty("IN_STOCK")
	private int iNSTOCK;

	@JsonProperty("CART_LIMIT")
	private int cARTLIMIT;

	@JsonProperty("UID")
	private int uID;

	@JsonProperty("IMAGE")
	private String iMAGE;

	@JsonProperty("ADDED_ON")
	private String aDDEDON;

	@JsonProperty("SUB_CAT_ID")
	private int sUBCATID;

	@JsonProperty("DESCRIPTION")
	private String dESCRIPTION;

	@JsonProperty("E_NAME")
	private String eNAME;

	@JsonProperty("COUNT")
	private int cOUNT;

	@JsonProperty("ID")
	private int iD;

	@JsonProperty("CID")
	private int cID;

	public void setTNAME(String tNAME){
		this.tNAME = tNAME;
	}

	public String getTNAME(){
		return tNAME;
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

	public void setOFFER(int oFFER){
		this.oFFER = oFFER;
	}

	public int getOFFER(){
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

	public void setCARTLIMIT(int cARTLIMIT){
		this.cARTLIMIT = cARTLIMIT;
	}

	public int getCARTLIMIT(){
		return cARTLIMIT;
	}

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

	public void setADDEDON(String aDDEDON){
		this.aDDEDON = aDDEDON;
	}

	public String getADDEDON(){
		return aDDEDON;
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

	public void setCOUNT(int cOUNT){
		this.cOUNT = cOUNT;
	}

	public int getCOUNT(){
		return cOUNT;
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
			"DATAItem{" + 
			"t_NAME = '" + tNAME + '\'' + 
			",pRICE = '" + pRICE + '\'' + 
			",oFFER_LIMIT = '" + oFFERLIMIT + '\'' + 
			",qUANTITY = '" + qUANTITY + '\'' + 
			",oFFER = '" + oFFER + '\'' + 
			",pID = '" + pID + '\'' + 
			",oID = '" + oID + '\'' + 
			",iN_STOCK = '" + iNSTOCK + '\'' + 
			",cART_LIMIT = '" + cARTLIMIT + '\'' + 
			",uID = '" + uID + '\'' + 
			",iMAGE = '" + iMAGE + '\'' + 
			",aDDED_ON = '" + aDDEDON + '\'' + 
			",sUB_CAT_ID = '" + sUBCATID + '\'' + 
			",dESCRIPTION = '" + dESCRIPTION + '\'' + 
			",e_NAME = '" + eNAME + '\'' + 
			",cOUNT = '" + cOUNT + '\'' + 
			",iD = '" + iD + '\'' + 
			",cID = '" + cID + '\'' + 
			"}";
		}
}
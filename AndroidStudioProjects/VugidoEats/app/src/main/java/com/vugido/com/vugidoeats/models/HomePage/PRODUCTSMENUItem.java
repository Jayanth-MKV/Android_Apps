package com.vugido.com.vugidoeats.models.HomePage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PRODUCTSMENUItem{

	@JsonProperty("T_NAME")
	private String tNAME;

	@JsonProperty("IMAGE")
	private String iMAGE;

	@JsonProperty("PRICE")
	private int pRICE;

	@JsonProperty("OFFER_LIMIT")
	private int oFFERLIMIT;

	@JsonProperty("QUANTITY")
	private String qUANTITY;

	@JsonProperty("DESCRIPTION")
	private String dESCRIPTION;

	@JsonProperty("OFFER")
	private int oFFER;

	@JsonProperty("E_NAME")
	private String eNAME;

	@JsonProperty("PID")
	private int pID;

	@JsonProperty("IN_STOCK")
	private int iNSTOCK;

	@JsonProperty("CART_LIMIT")
	private int cARTLIMIT;

	@JsonProperty("SID")
	private int sID;

	public void setTNAME(String tNAME){
		this.tNAME = tNAME;
	}

	public String getTNAME(){
		return tNAME;
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

	public void setQUANTITY(String qUANTITY){
		this.qUANTITY = qUANTITY;
	}

	public String getQUANTITY(){
		return qUANTITY;
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

	public void setSID(int sID){
		this.sID = sID;
	}

	public int getSID(){
		return sID;
	}

	@Override
 	public String toString(){
		return 
			"PRODUCTSMENUItem{" + 
			"t_NAME = '" + tNAME + '\'' + 
			",iMAGE = '" + iMAGE + '\'' + 
			",pRICE = '" + pRICE + '\'' + 
			",oFFER_LIMIT = '" + oFFERLIMIT + '\'' + 
			",qUANTITY = '" + qUANTITY + '\'' + 
			",dESCRIPTION = '" + dESCRIPTION + '\'' + 
			",oFFER = '" + oFFER + '\'' + 
			",e_NAME = '" + eNAME + '\'' + 
			",pID = '" + pID + '\'' + 
			",iN_STOCK = '" + iNSTOCK + '\'' + 
			",cART_LIMIT = '" + cARTLIMIT + '\'' + 
			",sID = '" + sID + '\'' + 
			"}";
		}
}
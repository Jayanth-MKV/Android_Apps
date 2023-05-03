package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.updated.cart.FetchCart;

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

	@JsonProperty("OFFER_STATUS")
	private int oFFERSTATUS;

	@JsonProperty("PID")
	private int pID;

	@JsonProperty("DATED")
	private String dATED;

	@JsonProperty("IN_STOCK")
	private int iNSTOCK;

	@JsonProperty("CART_ID")
	private int cARTID;

	@JsonProperty("CART_LIMIT")
	private int cARTLIMIT;

	@JsonProperty("SID")
	private int sID;

	@JsonProperty("IMAGE")
	private String iMAGE;

	@JsonProperty("DESCRIPTION")
	private String dESCRIPTION;

	@JsonProperty("E_NAME")
	private String eNAME;

	@JsonProperty("CART_QUANTITY")
	private int cARTQUANTITY;

	@JsonProperty("IS_GRAM_SET")
	private int iSGRAMSET;

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

	public void setOFFERSTATUS(int oFFERSTATUS){
		this.oFFERSTATUS = oFFERSTATUS;
	}

	public int getOFFERSTATUS(){
		return oFFERSTATUS;
	}

	public void setPID(int pID){
		this.pID = pID;
	}

	public int getPID(){
		return pID;
	}

	public void setDATED(String dATED){
		this.dATED = dATED;
	}

	public String getDATED(){
		return dATED;
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

	public void setENAME(String eNAME){
		this.eNAME = eNAME;
	}

	public String getENAME(){
		return eNAME;
	}

	public void setCARTQUANTITY(int cARTQUANTITY){
		this.cARTQUANTITY = cARTQUANTITY;
	}

	public int getCARTQUANTITY(){
		return cARTQUANTITY;
	}

	public void setISGRAMSET(int iSGRAMSET){
		this.iSGRAMSET = iSGRAMSET;
	}

	public int getISGRAMSET(){
		return iSGRAMSET;
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
			",oFFER_STATUS = '" + oFFERSTATUS + '\'' + 
			",pID = '" + pID + '\'' + 
			",dATED = '" + dATED + '\'' + 
			",iN_STOCK = '" + iNSTOCK + '\'' + 
			",cART_ID = '" + cARTID + '\'' + 
			",cART_LIMIT = '" + cARTLIMIT + '\'' + 
			",sID = '" + sID + '\'' + 
			",iMAGE = '" + iMAGE + '\'' + 
			",dESCRIPTION = '" + dESCRIPTION + '\'' + 
			",e_NAME = '" + eNAME + '\'' + 
			",cART_QUANTITY = '" + cARTQUANTITY + '\'' + 
			",iS_GRAM_SET = '" + iSGRAMSET + '\'' + 
			"}";
		}
}
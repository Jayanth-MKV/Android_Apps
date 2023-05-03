package com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.SquareMediumModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("T_NAME")
	private String tNAME;

	@JsonProperty("IMAGE")
	private String iMAGE;

	@JsonProperty("OFFER_LIMIT")
	private int oFFERLIMIT;

	@JsonProperty("PRICE")
	private int pRICE;

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

	@JsonProperty("ID")
	private int iD;

	@JsonProperty("CART_LIMIT")
	private int cARTLIMIT;

	@JsonProperty("CID")
	private int cID;

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

	public void setOFFERLIMIT(int oFFERLIMIT){
		this.oFFERLIMIT = oFFERLIMIT;
	}

	public int getOFFERLIMIT(){
		return oFFERLIMIT;
	}

	public void setPRICE(int pRICE){
		this.pRICE = pRICE;
	}

	public int getPRICE(){
		return pRICE;
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

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setCARTLIMIT(int cARTLIMIT){
		this.cARTLIMIT = cARTLIMIT;
	}

	public int getCARTLIMIT(){
		return cARTLIMIT;
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
			",iMAGE = '" + iMAGE + '\'' + 
			",oFFER_LIMIT = '" + oFFERLIMIT + '\'' + 
			",pRICE = '" + pRICE + '\'' + 
			",qUANTITY = '" + qUANTITY + '\'' + 
			",dESCRIPTION = '" + dESCRIPTION + '\'' + 
			",oFFER = '" + oFFER + '\'' + 
			",e_NAME = '" + eNAME + '\'' + 
			",pID = '" + pID + '\'' + 
			",iD = '" + iD + '\'' + 
			",cART_LIMIT = '" + cARTLIMIT + '\'' + 
			",cID = '" + cID + '\'' + 
			"}";
		}
}
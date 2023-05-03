package com.dailyneeds.vugido.models.SubCategoryModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class SubCategoryItem {

	@JsonProperty("T_NAME")
	private String tNAME;

	@JsonProperty("DEFAULT_SIZE")
	private int dEFAULTSIZE;

	@JsonProperty("QUANTITY_UNIT")
	private String qUANTITYUNIT;

	public String getqUANTITYUNIT() {
		return qUANTITYUNIT;
	}

	public String getqUANTITY() {
		return qUANTITY;
	}

	public void setqUANTITY(String qUANTITY) {
		this.qUANTITY = qUANTITY;
	}

	public void setqUANTITYUNIT(String qUANTITYUNIT) {
		this.qUANTITYUNIT = qUANTITYUNIT;
	}

	@JsonProperty("PRICE")
	private String pRICE;

	public String getpRICE() {
		return pRICE;
	}

	public void setpRICE(String pRICE) {
		this.pRICE = pRICE;
	}

	@JsonProperty("QUANTITY")
	private String qUANTITY;

	@JsonProperty("OFFER")
	private int oFFER;

	@JsonProperty("OFFER_STATUS")
	private int oFFERSTATUS;

	@JsonProperty("L")
	private int L;

	@JsonProperty("IN_STOCK")
	private int iNSTOCK;

	@JsonProperty("M")
	private int M;

	@JsonProperty("IMAGE")
	private String iMAGE;

	@JsonProperty("S")
	private int S;

	@JsonProperty("E_NAME")
	private String eNAME;

	@JsonProperty("TAG")
	private int tAG;

	@JsonProperty("ID")
	private int iD;

	@JsonProperty("QUALIFIER")
	private int qUALIFIER;

	@JsonProperty("UNIT_INTERVAL")
	private int uNITINTERVAL;

	@JsonProperty("CID")
	private String cID;

	public void setTNAME(String tNAME){
		this.tNAME = tNAME;
	}

	public String getTNAME(){
		return tNAME;
	}

	public void setDEFAULTSIZE(int dEFAULTSIZE){
		this.dEFAULTSIZE = dEFAULTSIZE;
	}

	public int getDEFAULTSIZE(){
		return dEFAULTSIZE;
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

	public void setL(int L){
		this.L = L;
	}

	public int getL(){
		return L;
	}

	public void setINSTOCK(int iNSTOCK){
		this.iNSTOCK = iNSTOCK;
	}

	public int getINSTOCK(){
		return iNSTOCK;
	}

	public void setM(int M){
		this.M = M;
	}

	public int getM(){
		return M;
	}

	public void setIMAGE(String iMAGE){
		this.iMAGE = iMAGE;
	}

	public String getIMAGE(){
		return iMAGE;
	}

	public void setS(int S){
		this.S = S;
	}

	public int getS(){
		return S;
	}

	public void setENAME(String eNAME){
		this.eNAME = eNAME;
	}

	public String getENAME(){
		return eNAME;
	}

	public void setTAG(int tAG){
		this.tAG = tAG;
	}

	public int getTAG(){
		return tAG;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setQUALIFIER(int qUALIFIER){
		this.qUALIFIER = qUALIFIER;
	}

	public int getQUALIFIER(){
		return qUALIFIER;
	}

	public void setUNITINTERVAL(int uNITINTERVAL){
		this.uNITINTERVAL = uNITINTERVAL;
	}

	public int getUNITINTERVAL(){
		return uNITINTERVAL;
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
			"SubCategoryItem{" +
			"t_NAME = '" + tNAME + '\'' + 
			",dEFAULT_SIZE = '" + dEFAULTSIZE + '\'' + 
			",qUANTITY_UNIT = '" + qUANTITYUNIT + '\'' + 
			",pRICE = '" + pRICE + '\'' + 
			",qUANTITY = '" + qUANTITY + '\'' + 
			",oFFER = '" + oFFER + '\'' + 
			",oFFER_STATUS = '" + oFFERSTATUS + '\'' + 
			",L = '" + L + '\'' + 
			",iN_STOCK = '" + iNSTOCK + '\'' + 
			",M = '" + M + '\'' + 
			",iMAGE = '" + iMAGE + '\'' + 
			",S = '" + S + '\'' + 
			",e_NAME = '" + eNAME + '\'' + 
			",tAG = '" + tAG + '\'' + 
			",iD = '" + iD + '\'' + 
			",qUALIFIER = '" + qUALIFIER + '\'' + 
			",uNIT_INTERVAL = '" + uNITINTERVAL + '\'' + 
			",cID = '" + cID + '\'' + 
			"}";
		}
}
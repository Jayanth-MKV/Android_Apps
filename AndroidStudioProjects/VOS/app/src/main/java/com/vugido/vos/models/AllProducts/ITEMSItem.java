package com.vugido.vos.models.AllProducts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ITEMSItem{

	@JsonProperty("T_NAME")
	private String tNAME;

	@JsonProperty("DEFAULT_SIZE")
	private int dEFAULTSIZE;

	@JsonProperty("PP")
	private String pP;

	@JsonProperty("QUANTITY_UNIT")
	private int qUANTITYUNIT;

	@JsonProperty("PRICE")
	private int pRICE;

	@JsonProperty("QUANTITY")
	private int qUANTITY;

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

	@JsonProperty("PPP_ID")
	private int pPPID;

	@JsonProperty("TAG")
	private int tAG;

	@JsonProperty("ID")
	private int iD;

	@JsonProperty("QUALIFIER")
	private int qUALIFIER;

	@JsonProperty("UNIT_INTERVAL")
	private int uNITINTERVAL;

	@JsonProperty("CID")
	private int cID;

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

	public void setPP(String pP){
		this.pP = pP;
	}

	public String getPP(){
		return pP;
	}

	public void setQUANTITYUNIT(int qUANTITYUNIT){
		this.qUANTITYUNIT = qUANTITYUNIT;
	}

	public int getQUANTITYUNIT(){
		return qUANTITYUNIT;
	}

	public void setPRICE(int pRICE){
		this.pRICE = pRICE;
	}

	public int getPRICE(){
		return pRICE;
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

	public void setPPPID(int pPPID){
		this.pPPID = pPPID;
	}

	public int getPPPID(){
		return pPPID;
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

	public void setCID(int cID){
		this.cID = cID;
	}

	public int getCID(){
		return cID;
	}

	@Override
 	public String toString(){
		return 
			"ITEMSItem{" + 
			"t_NAME = '" + tNAME + '\'' + 
			",dEFAULT_SIZE = '" + dEFAULTSIZE + '\'' + 
			",pP = '" + pP + '\'' + 
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
			",pPP_ID = '" + pPPID + '\'' + 
			",tAG = '" + tAG + '\'' + 
			",iD = '" + iD + '\'' + 
			",qUALIFIER = '" + qUALIFIER + '\'' + 
			",uNIT_INTERVAL = '" + uNITINTERVAL + '\'' + 
			",cID = '" + cID + '\'' + 
			"}";
		}
}
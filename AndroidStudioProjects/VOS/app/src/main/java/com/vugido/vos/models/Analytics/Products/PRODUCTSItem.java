package com.vugido.vos.models.Analytics.Products;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PRODUCTSItem{

	@JsonProperty("T_NAME")
	private String tNAME;

	@JsonProperty("PP")
	private String pP;

	@JsonProperty("QUANTITY")
	private int qUANTITY;

	@JsonProperty("P_PRICE")
	private int pPRICE;

	@JsonProperty("CAT")
	private int cAT;

	@JsonProperty("SIZE")
	private int sIZE;

	@JsonProperty("TOTAL_PRICE")
	private int tOTALPRICE;

	@JsonProperty("E_NAME")
	private String eNAME;

	@JsonProperty("PID")
	private int pID;

	@JsonProperty("ID")
	private int iD;

	@JsonProperty("QUALIFIER")
	private int qUALIFIER;

	public void setTNAME(String tNAME){
		this.tNAME = tNAME;
	}

	public String getTNAME(){
		return tNAME;
	}

	public void setPP(String pP){
		this.pP = pP;
	}

	public String getPP(){
		return pP;
	}

	public void setQUANTITY(int qUANTITY){
		this.qUANTITY = qUANTITY;
	}

	public int getQUANTITY(){
		return qUANTITY;
	}

	public void setPPRICE(int pPRICE){
		this.pPRICE = pPRICE;
	}

	public int getPPRICE(){
		return pPRICE;
	}

	public void setCAT(int cAT){
		this.cAT = cAT;
	}

	public int getCAT(){
		return cAT;
	}

	public void setSIZE(int sIZE){
		this.sIZE = sIZE;
	}

	public int getSIZE(){
		return sIZE;
	}

	public void setTOTALPRICE(int tOTALPRICE){
		this.tOTALPRICE = tOTALPRICE;
	}

	public int getTOTALPRICE(){
		return tOTALPRICE;
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

	public void setQUALIFIER(int qUALIFIER){
		this.qUALIFIER = qUALIFIER;
	}

	public int getQUALIFIER(){
		return qUALIFIER;
	}

	@Override
 	public String toString(){
		return 
			"PRODUCTSItem{" + 
			"t_NAME = '" + tNAME + '\'' + 
			",pP = '" + pP + '\'' + 
			",qUANTITY = '" + qUANTITY + '\'' + 
			",p_PRICE = '" + pPRICE + '\'' + 
			",cAT = '" + cAT + '\'' + 
			",sIZE = '" + sIZE + '\'' + 
			",tOTAL_PRICE = '" + tOTALPRICE + '\'' + 
			",e_NAME = '" + eNAME + '\'' + 
			",pID = '" + pID + '\'' + 
			",iD = '" + iD + '\'' + 
			",qUALIFIER = '" + qUALIFIER + '\'' + 
			"}";
		}
}
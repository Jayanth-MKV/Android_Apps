package com.vugido.vpm.model.active_orders;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataItem{

	@JsonProperty("DISTANCE")
	private double dISTANCE;

	@JsonProperty("MODE")
	private int mODE;

	@JsonProperty("TIME")
	private String tIME;

	@JsonProperty("LON")
	private double lON;

	@JsonProperty("OID")
	private int oID;

	@JsonProperty("UID")
	private int uID;

	@JsonProperty("DATE")
	private String dATE;

	@JsonProperty("STATUS")
	private int sTATUS;

	@JsonProperty("ADDRESS")
	private String aDDRESS;

	@JsonProperty("SLOT")
	private String sLOT;

	@JsonProperty("COUNT")
	private int cOUNT;

	@JsonProperty("TP")
	private int tP;

	@JsonProperty("LAT")
	private double lAT;

	@JsonProperty("DC")
	private int dC;

	public void setDISTANCE(double dISTANCE){
		this.dISTANCE = dISTANCE;
	}

	public double getDISTANCE(){
		return dISTANCE;
	}

	public void setMODE(int mODE){
		this.mODE = mODE;
	}

	public int getMODE(){
		return mODE;
	}

	public void setTIME(String tIME){
		this.tIME = tIME;
	}

	public String getTIME(){
		return tIME;
	}

	public void setLON(double lON){
		this.lON = lON;
	}

	public double getLON(){
		return lON;
	}

	public void setOID(int oID){
		this.oID = oID;
	}

	public int getOID(){
		return oID;
	}

	public void setUID(int uID){
		this.uID = uID;
	}

	public int getUID(){
		return uID;
	}

	public void setDATE(String dATE){
		this.dATE = dATE;
	}

	public String getDATE(){
		return dATE;
	}

	public void setSTATUS(int sTATUS){
		this.sTATUS = sTATUS;
	}

	public int getSTATUS(){
		return sTATUS;
	}

	public void setADDRESS(String aDDRESS){
		this.aDDRESS = aDDRESS;
	}

	public String getADDRESS(){
		return aDDRESS;
	}

	public void setSLOT(String sLOT){
		this.sLOT = sLOT;
	}

	public String getSLOT(){
		return sLOT;
	}

	public void setCOUNT(int cOUNT){
		this.cOUNT = cOUNT;
	}

	public int getCOUNT(){
		return cOUNT;
	}

	public void setTP(int tP){
		this.tP = tP;
	}

	public int getTP(){
		return tP;
	}

	public void setLAT(double lAT){
		this.lAT = lAT;
	}

	public double getLAT(){
		return lAT;
	}

	public void setDC(int dC){
		this.dC = dC;
	}

	public int getDC(){
		return dC;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"dISTANCE = '" + dISTANCE + '\'' + 
			",mODE = '" + mODE + '\'' + 
			",tIME = '" + tIME + '\'' + 
			",lON = '" + lON + '\'' + 
			",oID = '" + oID + '\'' + 
			",uID = '" + uID + '\'' + 
			",dATE = '" + dATE + '\'' + 
			",sTATUS = '" + sTATUS + '\'' + 
			",aDDRESS = '" + aDDRESS + '\'' + 
			",sLOT = '" + sLOT + '\'' + 
			",cOUNT = '" + cOUNT + '\'' + 
			",tP = '" + tP + '\'' + 
			",lAT = '" + lAT + '\'' + 
			",dC = '" + dC + '\'' + 
			"}";
		}
}
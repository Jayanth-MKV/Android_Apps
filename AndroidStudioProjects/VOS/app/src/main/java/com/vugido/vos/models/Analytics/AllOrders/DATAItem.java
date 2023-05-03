package com.vugido.vos.models.Analytics.AllOrders;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("LOCATION")
	private String lOCATION;

	@JsonProperty("DISTANCE")
	private double dISTANCE;

	@JsonProperty("DMODE")
	private int dMODE;

	@JsonProperty("LONGITUDE")
	private double lONGITUDE;

	@JsonProperty("TIME")
	private String tIME;

	@JsonProperty("OID")
	private int oID;

	@JsonProperty("UID")
	private int uID;

	@JsonProperty("DATE")
	private String dATE;

	@JsonProperty("STATUS")
	private int sTATUS;

	@JsonProperty("DELIVERY_CHARGE")
	private int dELIVERYCHARGE;

	@JsonProperty("TOTAL_PRICE")
	private double tOTALPRICE;

	@JsonProperty("SLOT")
	private int sLOT;

	@JsonProperty("COUNT")
	private int cOUNT;

	@JsonProperty("LATITUDE")
	private double lATITUDE;

	public void setLOCATION(String lOCATION){
		this.lOCATION = lOCATION;
	}

	public String getLOCATION(){
		return lOCATION;
	}

	public void setDISTANCE(double dISTANCE){
		this.dISTANCE = dISTANCE;
	}

	public double getDISTANCE(){
		return dISTANCE;
	}

	public void setDMODE(int dMODE){
		this.dMODE = dMODE;
	}

	public int getDMODE(){
		return dMODE;
	}

	public void setLONGITUDE(double lONGITUDE){
		this.lONGITUDE = lONGITUDE;
	}

	public double getLONGITUDE(){
		return lONGITUDE;
	}

	public void setTIME(String tIME){
		this.tIME = tIME;
	}

	public String getTIME(){
		return tIME;
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

	public void setDELIVERYCHARGE(int dELIVERYCHARGE){
		this.dELIVERYCHARGE = dELIVERYCHARGE;
	}

	public int getDELIVERYCHARGE(){
		return dELIVERYCHARGE;
	}

	public void setTOTALPRICE(double tOTALPRICE){
		this.tOTALPRICE = tOTALPRICE;
	}

	public double getTOTALPRICE(){
		return tOTALPRICE;
	}

	public void setSLOT(int sLOT){
		this.sLOT = sLOT;
	}

	public int getSLOT(){
		return sLOT;
	}

	public void setCOUNT(int cOUNT){
		this.cOUNT = cOUNT;
	}

	public int getCOUNT(){
		return cOUNT;
	}

	public void setLATITUDE(double lATITUDE){
		this.lATITUDE = lATITUDE;
	}

	public double getLATITUDE(){
		return lATITUDE;
	}

	@Override
 	public String toString(){
		return 
			"DATAItem{" + 
			"lOCATION = '" + lOCATION + '\'' + 
			",dISTANCE = '" + dISTANCE + '\'' + 
			",dMODE = '" + dMODE + '\'' + 
			",lONGITUDE = '" + lONGITUDE + '\'' + 
			",tIME = '" + tIME + '\'' + 
			",oID = '" + oID + '\'' + 
			",uID = '" + uID + '\'' + 
			",dATE = '" + dATE + '\'' + 
			",sTATUS = '" + sTATUS + '\'' + 
			",dELIVERY_CHARGE = '" + dELIVERYCHARGE + '\'' + 
			",tOTAL_PRICE = '" + tOTALPRICE + '\'' + 
			",sLOT = '" + sLOT + '\'' + 
			",cOUNT = '" + cOUNT + '\'' + 
			",lATITUDE = '" + lATITUDE + '\'' + 
			"}";
		}
}
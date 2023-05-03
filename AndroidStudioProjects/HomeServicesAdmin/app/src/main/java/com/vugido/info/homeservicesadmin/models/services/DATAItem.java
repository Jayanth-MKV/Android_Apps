package com.vugido.info.homeservicesadmin.models.services;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	public String getnAME() {
		return nAME;
	}

	public void setnAME(String nAME) {
		this.nAME = nAME;
	}

	@JsonProperty("NAME")
	private String nAME;

	@JsonProperty("SERVICE_NAME")
	private String sERVICENAME;

	@JsonProperty("UID")
	private UID uID;

	@JsonProperty("MESSAGE")
	private String mESSAGE;

	@JsonProperty("ORDERED_FTIME")
	private String oRDEREDFTIME;

	@JsonProperty("STATUS")
	private int sTATUS;

	@JsonProperty("ADDRESS")
	private String aDDRESS;

	@JsonProperty("LONGITUDE")
	private String lONGITUDE;

	@JsonProperty("OID")
	private int oID;

	@JsonProperty("ORDERED_ON")
	private String oRDEREDON;

	@JsonProperty("LATITUDE")
	private String lATITUDE;

	@JsonProperty("ORDERED_FDATE")
	private String oRDEREDFDATE;

	@JsonProperty("SID")
	private int sID;

	public void setSERVICENAME(String sERVICENAME){
		this.sERVICENAME = sERVICENAME;
	}

	public String getSERVICENAME(){
		return sERVICENAME;
	}

	public void setUID(UID uID){
		this.uID = uID;
	}

	public UID getUID(){
		return uID;
	}

	public void setMESSAGE(String mESSAGE){
		this.mESSAGE = mESSAGE;
	}

	public String getMESSAGE(){
		return mESSAGE;
	}

	public void setORDEREDFTIME(String oRDEREDFTIME){
		this.oRDEREDFTIME = oRDEREDFTIME;
	}

	public String getORDEREDFTIME(){
		return oRDEREDFTIME;
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

	public void setLONGITUDE(String lONGITUDE){
		this.lONGITUDE = lONGITUDE;
	}

	public String getLONGITUDE(){
		return lONGITUDE;
	}

	public void setOID(int oID){
		this.oID = oID;
	}

	public int getOID(){
		return oID;
	}

	public void setORDEREDON(String oRDEREDON){
		this.oRDEREDON = oRDEREDON;
	}

	public String getORDEREDON(){
		return oRDEREDON;
	}

	public void setLATITUDE(String lATITUDE){
		this.lATITUDE = lATITUDE;
	}

	public String getLATITUDE(){
		return lATITUDE;
	}

	public void setORDEREDFDATE(String oRDEREDFDATE){
		this.oRDEREDFDATE = oRDEREDFDATE;
	}

	public String getORDEREDFDATE(){
		return oRDEREDFDATE;
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
			"DATAItem{" + 
			"sERVICE_NAME = '" + sERVICENAME + '\'' + 
			",uID = '" + uID + '\'' + 
			",mESSAGE = '" + mESSAGE + '\'' + 
			",oRDERED_FTIME = '" + oRDEREDFTIME + '\'' + 
			",sTATUS = '" + sTATUS + '\'' + 
			",aDDRESS = '" + aDDRESS + '\'' + 
			",lONGITUDE = '" + lONGITUDE + '\'' + 
			",oID = '" + oID + '\'' + 
			",oRDERED_ON = '" + oRDEREDON + '\'' + 
			",lATITUDE = '" + lATITUDE + '\'' + 
			",oRDERED_FDATE = '" + oRDEREDFDATE + '\'' + 
			",sID = '" + sID + '\'' +
					",nAME = '" + nAME + '\'' +
					"}";
		}
}
package com.foodhub.vugido.models.homepage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SERVICECLIENTSItem{

	@JsonProperty("TYPE_ID")
	private int tYPEID;

	@JsonProperty("RATING")
	private String rATING;

	@JsonProperty("BUSINESS_NAME")
	private String bUSINESSNAME;

	@JsonProperty("OFFER")
	private int oFFER;

	@JsonProperty("MIN_PRICE")
	private int mINPRICE;

	@JsonProperty("LONGITUDE")
	private String lONGITUDE;

	@JsonProperty("LOGO")
	private String lOGO;

	@JsonProperty("ID")
	private int iD;

	@JsonProperty("LOCATION_NAME")
	private String lOCATIONNAME;

	@JsonProperty("LATITUDE")
	private String lATITUDE;

	public void setTYPEID(int tYPEID){
		this.tYPEID = tYPEID;
	}

	public int getTYPEID(){
		return tYPEID;
	}

	public void setRATING(String rATING){
		this.rATING = rATING;
	}

	public String getRATING(){
		return rATING;
	}

	public void setBUSINESSNAME(String bUSINESSNAME){
		this.bUSINESSNAME = bUSINESSNAME;
	}

	public String getBUSINESSNAME(){
		return bUSINESSNAME;
	}

	public void setOFFER(int oFFER){
		this.oFFER = oFFER;
	}

	public int getOFFER(){
		return oFFER;
	}

	public void setMINPRICE(int mINPRICE){
		this.mINPRICE = mINPRICE;
	}

	public int getMINPRICE(){
		return mINPRICE;
	}

	public void setLONGITUDE(String lONGITUDE){
		this.lONGITUDE = lONGITUDE;
	}

	public String getLONGITUDE(){
		return lONGITUDE;
	}

	public void setLOGO(String lOGO){
		this.lOGO = lOGO;
	}

	public String getLOGO(){
		return lOGO;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setLOCATIONNAME(String lOCATIONNAME){
		this.lOCATIONNAME = lOCATIONNAME;
	}

	public String getLOCATIONNAME(){
		return lOCATIONNAME;
	}

	public void setLATITUDE(String lATITUDE){
		this.lATITUDE = lATITUDE;
	}

	public String getLATITUDE(){
		return lATITUDE;
	}

	@Override
 	public String toString(){
		return 
			"SERVICECLIENTSItem{" + 
			"tYPE_ID = '" + tYPEID + '\'' + 
			",rATING = '" + rATING + '\'' + 
			",bUSINESS_NAME = '" + bUSINESSNAME + '\'' + 
			",oFFER = '" + oFFER + '\'' + 
			",mIN_PRICE = '" + mINPRICE + '\'' + 
			",lONGITUDE = '" + lONGITUDE + '\'' + 
			",lOGO = '" + lOGO + '\'' + 
			",iD = '" + iD + '\'' + 
			",lOCATION_NAME = '" + lOCATIONNAME + '\'' + 
			",lATITUDE = '" + lATITUDE + '\'' + 
			"}";
		}
}
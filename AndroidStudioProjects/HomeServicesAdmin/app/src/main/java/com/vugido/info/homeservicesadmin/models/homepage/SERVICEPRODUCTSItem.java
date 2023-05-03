package com.vugido.info.homeservicesadmin.models.homepage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SERVICEPRODUCTSItem{

	@JsonProperty("IMAGE")
	private String iMAGE;

	@JsonProperty("DESCRIPTION")
	private String dESCRIPTION;

	@JsonProperty("TITLE")
	private String tITLE;

	@JsonProperty("ID")
	private int iD;

	@JsonProperty("TAGS")
	private String tAGS;

	@JsonProperty("SID")
	private int sID;

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

	public void setTITLE(String tITLE){
		this.tITLE = tITLE;
	}

	public String getTITLE(){
		return tITLE;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setTAGS(String tAGS){
		this.tAGS = tAGS;
	}

	public String getTAGS(){
		return tAGS;
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
			"SERVICEPRODUCTSItem{" + 
			"iMAGE = '" + iMAGE + '\'' + 
			",dESCRIPTION = '" + dESCRIPTION + '\'' + 
			",tITLE = '" + tITLE + '\'' + 
			",iD = '" + iD + '\'' + 
			",tAGS = '" + tAGS + '\'' + 
			",sID = '" + sID + '\'' + 
			"}";
		}
}
package com.vugido.info.homeservicesadmin.models.services.c;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("ACTIVE")
	private int aCTIVE;

	@JsonProperty("ID")
	private int iD;

	@JsonProperty("NAME")
	private String nAME;

	public void setACTIVE(int aCTIVE){
		this.aCTIVE = aCTIVE;
	}

	public int getACTIVE(){
		return aCTIVE;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setNAME(String nAME){
		this.nAME = nAME;
	}

	public String getNAME(){
		return nAME;
	}

	@Override
 	public String toString(){
		return 
			"DATAItem{" + 
			"aCTIVE = '" + aCTIVE + '\'' + 
			",iD = '" + iD + '\'' + 
			",nAME = '" + nAME + '\'' + 
			"}";
		}
}
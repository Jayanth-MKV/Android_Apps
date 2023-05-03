package com.vugido.online_groceries.models.homepage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SERVICECLIENTSTYPEItem{

	@JsonProperty("DESCRIBER")
	private String dESCRIBER;

	@JsonProperty("TITLE")
	private String tITLE;

	@JsonProperty("ID")
	private int iD;

	public void setDESCRIBER(String dESCRIBER){
		this.dESCRIBER = dESCRIBER;
	}

	public String getDESCRIBER(){
		return dESCRIBER;
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

	@Override
 	public String toString(){
		return 
			"SERVICECLIENTSTYPEItem{" + 
			"dESCRIBER = '" + dESCRIBER + '\'' + 
			",tITLE = '" + tITLE + '\'' + 
			",iD = '" + iD + '\'' + 
			"}";
		}
}
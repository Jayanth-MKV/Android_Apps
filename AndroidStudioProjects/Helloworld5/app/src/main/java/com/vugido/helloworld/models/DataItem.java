package com.vugido.helloworld.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataItem{

	@JsonProperty("ROLE")
	private String rOLE;

	@JsonProperty("ID")
	private int iD;

	@JsonProperty("PHNO")
	private int pHNO;

	@JsonProperty("NAME")
	private String nAME;

	@JsonProperty("MailID")
	private String mailID;

	public void setROLE(String rOLE){
		this.rOLE = rOLE;
	}

	public String getROLE(){
		return rOLE;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setPHNO(int pHNO){
		this.pHNO = pHNO;
	}

	public int getPHNO(){
		return pHNO;
	}

	public void setNAME(String nAME){
		this.nAME = nAME;
	}

	public String getNAME(){
		return nAME;
	}

	public void setMailID(String mailID){
		this.mailID = mailID;
	}

	public String getMailID(){
		return mailID;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"rOLE = '" + rOLE + '\'' + 
			",iD = '" + iD + '\'' + 
			",pHNO = '" + pHNO + '\'' + 
			",nAME = '" + nAME + '\'' + 
			",mailID = '" + mailID + '\'' + 
			"}";
		}
}
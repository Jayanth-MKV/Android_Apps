package com.thetimeofstudent.models.MAIN_MODEL;


public class SECTIONSItem{

	private int iMAGE;

	private String cNAME;

	private int sID;

	public SECTIONSItem(int iMAGE, String cNAME, int sID) {
		this.iMAGE = iMAGE;
		this.cNAME = cNAME;
		this.sID = sID;
	}

	public void setIMAGE(int iMAGE){
		this.iMAGE = iMAGE;
	}

	public int getIMAGE(){
		return iMAGE;
	}

	public void setCNAME(String cNAME){
		this.cNAME = cNAME;
	}

	public String getCNAME(){
		return cNAME;
	}

	public void setSID(int sID){
		this.sID = sID;
	}

	public int getSID(){
		return sID;
	}


}
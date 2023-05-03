package com.thetimeofstudent.models.MAIN_MODEL;


public class HOMEPRODUCTSItem{

	private int iMAGE;


	public HOMEPRODUCTSItem(int iMAGE, String dESCRIPTION, String eNAME, int pID, int sID) {
		this.iMAGE = iMAGE;
		this.dESCRIPTION = dESCRIPTION;
		this.eNAME = eNAME;
		this.pID = pID;
		this.sID = sID;
	}

	private String dESCRIPTION;



	private String eNAME;

	private int pID;


	private int sID;

	public void setIMAGE(int iMAGE){
		this.iMAGE = iMAGE;
	}

	public int getIMAGE(){
		return iMAGE;
	}


	public void setDESCRIPTION(String dESCRIPTION){
		this.dESCRIPTION = dESCRIPTION;
	}

	public String getDESCRIPTION(){
		return dESCRIPTION;
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


	public void setSID(int sID){
		this.sID = sID;
	}

	public int getSID(){
		return sID;
	}


}
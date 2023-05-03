package com.foodhub.vugido.models.homepage;


public class HOMEPRODUCTSItem{


	private SERVICECLIENTSItem serviceclientsItem;

	public HOMEPRODUCTSItem(SERVICECLIENTSItem serviceclientsItem) {
		this.serviceclientsItem = serviceclientsItem;

	}


	private String dESCRIPTION;



	private String eNAME;

	private int pID;


	private int sID;



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
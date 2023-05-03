package com.foodhub.vugido.models.homepage;


public class SECTIONSItem{


	private String cNAME;

	private int sID;

	private String des;

	public SECTIONSItem( String cNAME, int sID, String des) {
		this.cNAME = cNAME;
		this.sID = sID;
		this.des = des;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
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
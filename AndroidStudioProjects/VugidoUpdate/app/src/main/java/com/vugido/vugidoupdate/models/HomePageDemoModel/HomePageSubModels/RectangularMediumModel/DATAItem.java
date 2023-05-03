package com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.RectangularMediumModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("BUSINESS_NAME")
	private String bUSINESSNAME;

	@JsonProperty("LOGO")
	private String lOGO;

	@JsonProperty("ID")
	private int iD;

	@JsonProperty("CID")
	private int cID;

	public void setBUSINESSNAME(String bUSINESSNAME){
		this.bUSINESSNAME = bUSINESSNAME;
	}

	public String getBUSINESSNAME(){
		return bUSINESSNAME;
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

	public void setCID(int cID){
		this.cID = cID;
	}

	public int getCID(){
		return cID;
	}

	@Override
 	public String toString(){
		return 
			"DATAItem{" + 
			"bUSINESS_NAME = '" + bUSINESSNAME + '\'' + 
			",lOGO = '" + lOGO + '\'' + 
			",iD = '" + iD + '\'' + 
			",cID = '" + cID + '\'' + 
			"}";
		}
}
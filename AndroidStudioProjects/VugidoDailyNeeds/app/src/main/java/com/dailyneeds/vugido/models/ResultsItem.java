package com.dailyneeds.vugido.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ResultsItem{

	@JsonProperty("ID")
	private int iD;

	@JsonProperty("ICON")
	private String iCON;

	@JsonProperty("NAME")
	private String nAME;

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setICON(String iCON){
		this.iCON = iCON;
	}

	public String getICON(){
		return iCON;
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
			"ResultsItem{" + 
			"iD = '" + iD + '\'' + 
			",iCON = '" + iCON + '\'' + 
			",nAME = '" + nAME + '\'' + 
			"}";
		}
}
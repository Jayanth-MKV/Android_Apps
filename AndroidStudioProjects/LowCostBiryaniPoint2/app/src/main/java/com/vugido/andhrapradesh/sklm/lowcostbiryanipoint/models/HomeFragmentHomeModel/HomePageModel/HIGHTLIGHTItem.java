package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.HomeFragmentHomeModel.HomePageModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HIGHTLIGHTItem{

	@JsonProperty("IMAGE")
	private String iMAGE;

	@JsonProperty("DESCRIPTION")
	private String dESCRIPTION;

	@JsonProperty("PID")
	private int pID;

	@JsonProperty("CID")
	private int cID;

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

	public void setPID(int pID){
		this.pID = pID;
	}

	public int getPID(){
		return pID;
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
			"HIGHTLIGHTItem{" + 
			"iMAGE = '" + iMAGE + '\'' + 
			",dESCRIPTION = '" + dESCRIPTION + '\'' + 
			",pID = '" + pID + '\'' + 
			",cID = '" + cID + '\'' + 
			"}";
		}
}
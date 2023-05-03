package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.ServiceAvailability;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LOCATIONSItem{

	@JsonProperty("ZIP")
	private int zIP;

	@JsonProperty("LOCATION")
	private String lOCATION;

	@JsonProperty("LID")
	private int lID;

	public void setZIP(int zIP){
		this.zIP = zIP;
	}

	public int getZIP(){
		return zIP;
	}

	public void setLOCATION(String lOCATION){
		this.lOCATION = lOCATION;
	}

	public String getLOCATION(){
		return lOCATION;
	}

	public void setLID(int lID){
		this.lID = lID;
	}

	public int getLID(){
		return lID;
	}

	@Override
 	public String toString(){
		return 
			"LOCATIONSItem{" + 
			"zIP = '" + zIP + '\'' + 
			",lOCATION = '" + lOCATION + '\'' + 
			",lID = '" + lID + '\'' + 
			"}";
		}
}
package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.HomeFragmentHomeModel.HomePageModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TOPVIEWSItem{

	@JsonProperty("IMAGE")
	private String iMAGE;

	@JsonProperty("BUTTON")
	private int bUTTON;

	@JsonProperty("ID")
	private int iD;

	@JsonProperty("URL")
	private String uRL;

	@JsonProperty("TITLE")
	private String tITLE;

	public String gettITLE() {
		return tITLE;
	}

	public void settITLE(String tITLE) {
		this.tITLE = tITLE;
	}

	public void setIMAGE(String iMAGE){
		this.iMAGE = iMAGE;
	}

	public String getIMAGE(){
		return iMAGE;
	}

	public void setBUTTON(int bUTTON){
		this.bUTTON = bUTTON;
	}

	public int getBUTTON(){
		return bUTTON;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setURL(String uRL){
		this.uRL = uRL;
	}

	public String getURL(){
		return uRL;
	}

	@Override
 	public String toString(){
		return 
			"TOPVIEWSItem{" + 
			"iMAGE = '" + iMAGE + '\'' + 
			",bUTTON = '" + bUTTON + '\'' + 
			",iD = '" + iD + '\'' + 
			",uRL = '" + uRL + '\'' +
					",tITLE = '" + tITLE + '\'' +
					"}";
		}
}
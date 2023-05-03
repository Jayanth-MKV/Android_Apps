package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.OffersPageModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SLIDERItem{

	@JsonProperty("IMAGE")
	private String iMAGE;

	@JsonProperty("OFFER_ID")
	private int oFFERID;

	@JsonProperty("TITLE")
	private String tITLE;

	public void setIMAGE(String iMAGE){
		this.iMAGE = iMAGE;
	}

	public String getIMAGE(){
		return iMAGE;
	}

	public void setOFFERID(int oFFERID){
		this.oFFERID = oFFERID;
	}

	public int getOFFERID(){
		return oFFERID;
	}

	public void setTITLE(String tITLE){
		this.tITLE = tITLE;
	}

	public String getTITLE(){
		return tITLE;
	}

	@Override
 	public String toString(){
		return 
			"SLIDERItem{" + 
			"iMAGE = '" + iMAGE + '\'' + 
			",oFFER_ID = '" + oFFERID + '\'' + 
			",tITLE = '" + tITLE + '\'' + 
			"}";
		}
}
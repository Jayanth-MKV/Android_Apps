package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.CoinsModel;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DATA{

	@JsonProperty("COINS_EARNABLE_PRODUCTS")
	private List<COINSEARNABLEPRODUCTSItem> cOINSEARNABLEPRODUCTS;

	@JsonProperty("COINS_SPENDING")
	private List<COINSSPENDINGItem> cOINSSPENDING;

	@JsonProperty("COINS_DATA")
	private List<COINSDATAItem> cOINSDATA;

	public void setCOINSEARNABLEPRODUCTS(List<COINSEARNABLEPRODUCTSItem> cOINSEARNABLEPRODUCTS){
		this.cOINSEARNABLEPRODUCTS = cOINSEARNABLEPRODUCTS;
	}

	public List<COINSEARNABLEPRODUCTSItem> getCOINSEARNABLEPRODUCTS(){
		return cOINSEARNABLEPRODUCTS;
	}

	public void setCOINSSPENDING(List<COINSSPENDINGItem> cOINSSPENDING){
		this.cOINSSPENDING = cOINSSPENDING;
	}

	public List<COINSSPENDINGItem> getCOINSSPENDING(){
		return cOINSSPENDING;
	}

	public void setCOINSDATA(List<COINSDATAItem> cOINSDATA){
		this.cOINSDATA = cOINSDATA;
	}

	public List<COINSDATAItem> getCOINSDATA(){
		return cOINSDATA;
	}

	@Override
 	public String toString(){
		return 
			"DATA{" + 
			"cOINS_EARNABLE_PRODUCTS = '" + cOINSEARNABLEPRODUCTS + '\'' + 
			",cOINS_SPENDING = '" + cOINSSPENDING + '\'' + 
			",cOINS_DATA = '" + cOINSDATA + '\'' + 
			"}";
		}
}
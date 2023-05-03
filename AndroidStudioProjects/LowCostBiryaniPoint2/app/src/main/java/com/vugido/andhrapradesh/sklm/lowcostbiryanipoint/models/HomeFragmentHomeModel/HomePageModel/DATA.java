package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.HomeFragmentHomeModel.HomePageModel;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DATA{

	@JsonProperty("HIGHT_LIGHT")
	private List<HIGHTLIGHTItem> hIGHTLIGHT;

	@JsonProperty("ALL_PRODUCTS")
	private List<ALLPRODUCTSItem> aLLPRODUCTS;

	@JsonProperty("TOP_VIEWS")
	private List<TOPVIEWSItem> tOPVIEWS;

	@JsonProperty("CATEGORIES")
	private List<CATEGORIESItem> cATEGORIES;

	public void setHIGHTLIGHT(List<HIGHTLIGHTItem> hIGHTLIGHT){
		this.hIGHTLIGHT = hIGHTLIGHT;
	}

	public List<HIGHTLIGHTItem> getHIGHTLIGHT(){
		return hIGHTLIGHT;
	}

	public void setALLPRODUCTS(List<ALLPRODUCTSItem> aLLPRODUCTS){
		this.aLLPRODUCTS = aLLPRODUCTS;
	}

	public List<ALLPRODUCTSItem> getALLPRODUCTS(){
		return aLLPRODUCTS;
	}

	public void setTOPVIEWS(List<TOPVIEWSItem> tOPVIEWS){
		this.tOPVIEWS = tOPVIEWS;
	}

	public List<TOPVIEWSItem> getTOPVIEWS(){
		return tOPVIEWS;
	}

	public void setCATEGORIES(List<CATEGORIESItem> cATEGORIES){
		this.cATEGORIES = cATEGORIES;
	}

	public List<CATEGORIESItem> getCATEGORIES(){
		return cATEGORIES;
	}

	@Override
 	public String toString(){
		return 
			"DATA{" + 
			"hIGHT_LIGHT = '" + hIGHTLIGHT + '\'' + 
			",aLL_PRODUCTS = '" + aLLPRODUCTS + '\'' + 
			",tOP_VIEWS = '" + tOPVIEWS + '\'' + 
			",cATEGORIES = '" + cATEGORIES + '\'' + 
			"}";
		}
}
package com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.models.HomePageModel;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DATA{

	@JsonProperty("HIGHT_LIGHT")
	private List<HIGHTLIGHTItem> hIGHTLIGHT;

	@JsonProperty("ALL_PRODUCTS")
	private List<ALLPRODUCTSItem> aLLPRODUCTS;

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

	@Override
 	public String toString(){
		return 
			"DATA{" + 
			"hIGHT_LIGHT = '" + hIGHTLIGHT + '\'' + 
			",aLL_PRODUCTS = '" + aLLPRODUCTS + '\'' + 
			"}";
		}
}
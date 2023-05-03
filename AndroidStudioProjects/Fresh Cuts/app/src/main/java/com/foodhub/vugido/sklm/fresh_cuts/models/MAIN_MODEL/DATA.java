package com.foodhub.vugido.sklm.fresh_cuts.models.MAIN_MODEL;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DATA{

	@JsonProperty("HIGHT_LIGHT")
	private List<HIGHTLIGHTItem> hIGHTLIGHT;

	@JsonProperty("SLIDERS")
	private List<SLIDERSItem> sLIDERS;

	@JsonProperty("SECTIONS")
	private List<SECTIONSItem> sECTIONS;

	@JsonProperty("HOME_PRODUCTS")
	private List<HOMEPRODUCTSItem> hOMEPRODUCTS;

	@JsonProperty("CATEGORIES")
	private List<CATEGORIESItem> cATEGORIES;

	public void setHIGHTLIGHT(List<HIGHTLIGHTItem> hIGHTLIGHT){
		this.hIGHTLIGHT = hIGHTLIGHT;
	}

	public List<HIGHTLIGHTItem> getHIGHTLIGHT(){
		return hIGHTLIGHT;
	}

	public void setSLIDERS(List<SLIDERSItem> sLIDERS){
		this.sLIDERS = sLIDERS;
	}

	public List<SLIDERSItem> getSLIDERS(){
		return sLIDERS;
	}

	public void setSECTIONS(List<SECTIONSItem> sECTIONS){
		this.sECTIONS = sECTIONS;
	}

	public List<SECTIONSItem> getSECTIONS(){
		return sECTIONS;
	}

	public void setHOMEPRODUCTS(List<HOMEPRODUCTSItem> hOMEPRODUCTS){
		this.hOMEPRODUCTS = hOMEPRODUCTS;
	}

	public List<HOMEPRODUCTSItem> getHOMEPRODUCTS(){
		return hOMEPRODUCTS;
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
			",sLIDERS = '" + sLIDERS + '\'' + 
			",sECTIONS = '" + sECTIONS + '\'' + 
			",hOME_PRODUCTS = '" + hOMEPRODUCTS + '\'' + 
			",cATEGORIES = '" + cATEGORIES + '\'' + 
			"}";
		}
}
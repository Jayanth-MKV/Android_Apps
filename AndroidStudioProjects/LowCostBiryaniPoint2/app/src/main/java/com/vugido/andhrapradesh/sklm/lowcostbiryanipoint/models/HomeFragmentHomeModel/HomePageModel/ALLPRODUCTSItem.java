package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.HomeFragmentHomeModel.HomePageModel;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ALLPRODUCTSItem{

	@JsonProperty("SNACKS")
	private List<SNACKSItem> sNACKS;

	@JsonProperty("DAIRY_PRODUCTS")
	private List<DAIRYPRODUCTSItem> dAIRYPRODUCTS;

	@JsonProperty("DRY_FRUITS")
	private List<DRYFRUITSItem> dRYFRUITS;

	@JsonProperty("FOODS")
	private List<FOODSItem> fOODS;

	@JsonProperty("GROCERIES")
	private List<GROCERIESItem> gROCERIES;

	@JsonProperty("NON_VEG")
	private List<NONVEGItem> nONVEG;

	@JsonProperty("FRUITS")
	private List<FRUITSItem> fRUITS;

	@JsonProperty("VEGETABLES")
	private List<VEGETABLESItem> vEGETABLES;

	public void setSNACKS(List<SNACKSItem> sNACKS){
		this.sNACKS = sNACKS;
	}

	public List<SNACKSItem> getSNACKS(){
		return sNACKS;
	}

	public void setDAIRYPRODUCTS(List<DAIRYPRODUCTSItem> dAIRYPRODUCTS){
		this.dAIRYPRODUCTS = dAIRYPRODUCTS;
	}

	public List<DAIRYPRODUCTSItem> getDAIRYPRODUCTS(){
		return dAIRYPRODUCTS;
	}

	public void setDRYFRUITS(List<DRYFRUITSItem> dRYFRUITS){
		this.dRYFRUITS = dRYFRUITS;
	}

	public List<DRYFRUITSItem> getDRYFRUITS(){
		return dRYFRUITS;
	}

	public void setFOODS(List<FOODSItem> fOODS){
		this.fOODS = fOODS;
	}

	public List<FOODSItem> getFOODS(){
		return fOODS;
	}

	public void setGROCERIES(List<GROCERIESItem> gROCERIES){
		this.gROCERIES = gROCERIES;
	}

	public List<GROCERIESItem> getGROCERIES(){
		return gROCERIES;
	}

	public void setNONVEG(List<NONVEGItem> nONVEG){
		this.nONVEG = nONVEG;
	}

	public List<NONVEGItem> getNONVEG(){
		return nONVEG;
	}

	public void setFRUITS(List<FRUITSItem> fRUITS){
		this.fRUITS = fRUITS;
	}

	public List<FRUITSItem> getFRUITS(){
		return fRUITS;
	}

	public void setVEGETABLES(List<VEGETABLESItem> vEGETABLES){
		this.vEGETABLES = vEGETABLES;
	}

	public List<VEGETABLESItem> getVEGETABLES(){
		return vEGETABLES;
	}

	@Override
 	public String toString(){
		return 
			"ALLPRODUCTSItem{" + 
			"sNACKS = '" + sNACKS + '\'' + 
			",dAIRY_PRODUCTS = '" + dAIRYPRODUCTS + '\'' + 
			",dRY_FRUITS = '" + dRYFRUITS + '\'' + 
			",fOODS = '" + fOODS + '\'' + 
			",gROCERIES = '" + gROCERIES + '\'' + 
			",nON_VEG = '" + nONVEG + '\'' + 
			",fRUITS = '" + fRUITS + '\'' + 
			",vEGETABLES = '" + vEGETABLES + '\'' + 
			"}";
		}
}
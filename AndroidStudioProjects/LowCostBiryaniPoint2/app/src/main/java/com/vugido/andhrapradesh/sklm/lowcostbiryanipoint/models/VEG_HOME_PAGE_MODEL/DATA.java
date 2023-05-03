package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.VEG_HOME_PAGE_MODEL;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class DATA{

	@JsonProperty("ALL_PRODUCTS")
	private List<ALLPRODUCTSItem> aLLPRODUCTS;

	@JsonProperty("MINI_BANNER")
	private MINIBANNER mINIBANNER;

	@JsonProperty("OFFERS")
	private List<OFFERSItem> oFFERS;

	@JsonProperty("CATEGORIES")
	private List<CATEGORIESItem> cATEGORIES;

	public void setALLPRODUCTS(List<ALLPRODUCTSItem> aLLPRODUCTS){
		this.aLLPRODUCTS = aLLPRODUCTS;
	}

	public List<ALLPRODUCTSItem> getALLPRODUCTS(){
		return aLLPRODUCTS;
	}

	public void setMINIBANNER(MINIBANNER mINIBANNER){
		this.mINIBANNER = mINIBANNER;
	}

	public MINIBANNER getMINIBANNER(){
		return mINIBANNER;
	}

	public void setOFFERS(List<OFFERSItem> oFFERS){
		this.oFFERS = oFFERS;
	}

	public List<OFFERSItem> getOFFERS(){
		return oFFERS;
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
			"aLL_PRODUCTS = '" + aLLPRODUCTS + '\'' + 
			",mINI_BANNER = '" + mINIBANNER + '\'' + 
			",oFFERS = '" + oFFERS + '\'' + 
			",cATEGORIES = '" + cATEGORIES + '\'' + 
			"}";
		}
}
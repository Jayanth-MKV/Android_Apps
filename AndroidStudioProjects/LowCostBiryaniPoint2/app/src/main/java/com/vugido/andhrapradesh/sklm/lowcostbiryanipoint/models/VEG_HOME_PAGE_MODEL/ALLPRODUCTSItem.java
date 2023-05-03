package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.VEG_HOME_PAGE_MODEL;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ALLPRODUCTSItem{

	@JsonProperty("SPECIAL_VEGETABLES")
	private List<SPECIALVEGETABLESItem> sPECIALVEGETABLES;

	@JsonProperty("LEAFY_VEGETABLES")
	private List<LEAFYVEGETABLESItem> lEAFYVEGETABLES;

	@JsonProperty("SEASONAL_VEGETABLES")
	private List<SEASONALVEGETABLESItem> sEASONALVEGETABLES;

	@JsonProperty("COMMON_VEGETABLES")
	private List<COMMONVEGETABLESItem> cOMMONVEGETABLES;

	public void setSPECIALVEGETABLES(List<SPECIALVEGETABLESItem> sPECIALVEGETABLES){
		this.sPECIALVEGETABLES = sPECIALVEGETABLES;
	}

	public List<SPECIALVEGETABLESItem> getSPECIALVEGETABLES(){
		return sPECIALVEGETABLES;
	}

	public void setLEAFYVEGETABLES(List<LEAFYVEGETABLESItem> lEAFYVEGETABLES){
		this.lEAFYVEGETABLES = lEAFYVEGETABLES;
	}

	public List<LEAFYVEGETABLESItem> getLEAFYVEGETABLES(){
		return lEAFYVEGETABLES;
	}

	public void setSEASONALVEGETABLES(List<SEASONALVEGETABLESItem> sEASONALVEGETABLES){
		this.sEASONALVEGETABLES = sEASONALVEGETABLES;
	}

	public List<SEASONALVEGETABLESItem> getSEASONALVEGETABLES(){
		return sEASONALVEGETABLES;
	}

	public void setCOMMONVEGETABLES(List<COMMONVEGETABLESItem> cOMMONVEGETABLES){
		this.cOMMONVEGETABLES = cOMMONVEGETABLES;
	}

	public List<COMMONVEGETABLESItem> getCOMMONVEGETABLES(){
		return cOMMONVEGETABLES;
	}

	@Override
 	public String toString(){
		return 
			"ALLPRODUCTSItem{" + 
			"sPECIAL_VEGETABLES = '" + sPECIALVEGETABLES + '\'' + 
			",lEAFY_VEGETABLES = '" + lEAFYVEGETABLES + '\'' + 
			",sEASONAL_VEGETABLES = '" + sEASONALVEGETABLES + '\'' + 
			",cOMMON_VEGETABLES = '" + cOMMONVEGETABLES + '\'' + 
			"}";
		}
}
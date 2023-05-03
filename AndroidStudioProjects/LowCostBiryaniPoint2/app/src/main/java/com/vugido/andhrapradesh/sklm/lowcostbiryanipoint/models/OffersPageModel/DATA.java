package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.OffersPageModel;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DATA{

	@JsonProperty("SINGLE_PRODUCTS")
	private List<SINGLEPRODUCTSItem> sINGLEPRODUCTS;

	@JsonProperty("SLIDER")
	private List<SLIDERItem> sLIDER;

	public void setSINGLEPRODUCTS(List<SINGLEPRODUCTSItem> sINGLEPRODUCTS){
		this.sINGLEPRODUCTS = sINGLEPRODUCTS;
	}

	public List<SINGLEPRODUCTSItem> getSINGLEPRODUCTS(){
		return sINGLEPRODUCTS;
	}

	public void setSLIDER(List<SLIDERItem> sLIDER){
		this.sLIDER = sLIDER;
	}

	public List<SLIDERItem> getSLIDER(){
		return sLIDER;
	}

	@Override
 	public String toString(){
		return 
			"DATA{" + 
			"sINGLE_PRODUCTS = '" + sINGLEPRODUCTS + '\'' + 
			",sLIDER = '" + sLIDER + '\'' + 
			"}";
		}
}
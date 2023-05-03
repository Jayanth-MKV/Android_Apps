package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.HomeFragmentHomeModel;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ALLPRODUCTSItem{

	@JsonProperty("NON_VEG_CURRIES")
	private List<NONVEGCURRIESItem> nONVEGCURRIES;

	@JsonProperty("VEG_CURRIES")
	private List<VEGCURRIESItem> vEGCURRIES;

	@JsonProperty("ROASTED_ITEMS")
	private List<ROASTEDITEMSItem> rOASTEDITEMS;

	@JsonProperty("STARTERS")
	private List<STARTERSItem> sTARTERS;

	@JsonProperty("RICE_ITEMS")
	private List<RICEITEMSItem> rICEITEMS;

	public void setNONVEGCURRIES(List<NONVEGCURRIESItem> nONVEGCURRIES){
		this.nONVEGCURRIES = nONVEGCURRIES;
	}

	public List<NONVEGCURRIESItem> getNONVEGCURRIES(){
		return nONVEGCURRIES;
	}

	public void setVEGCURRIES(List<VEGCURRIESItem> vEGCURRIES){
		this.vEGCURRIES = vEGCURRIES;
	}

	public List<VEGCURRIESItem> getVEGCURRIES(){
		return vEGCURRIES;
	}

	public void setROASTEDITEMS(List<ROASTEDITEMSItem> rOASTEDITEMS){
		this.rOASTEDITEMS = rOASTEDITEMS;
	}

	public List<ROASTEDITEMSItem> getROASTEDITEMS(){
		return rOASTEDITEMS;
	}

	public void setSTARTERS(List<STARTERSItem> sTARTERS){
		this.sTARTERS = sTARTERS;
	}

	public List<STARTERSItem> getSTARTERS(){
		return sTARTERS;
	}

	public void setRICEITEMS(List<RICEITEMSItem> rICEITEMS){
		this.rICEITEMS = rICEITEMS;
	}

	public List<RICEITEMSItem> getRICEITEMS(){
		return rICEITEMS;
	}

	@Override
 	public String toString(){
		return 
			"ALLPRODUCTSItem{" + 
			"nON_VEG_CURRIES = '" + nONVEGCURRIES + '\'' + 
			",vEG_CURRIES = '" + vEGCURRIES + '\'' + 
			",rOASTED_ITEMS = '" + rOASTEDITEMS + '\'' + 
			",sTARTERS = '" + sTARTERS + '\'' + 
			",rICE_ITEMS = '" + rICEITEMS + '\'' + 
			"}";
		}
}
package com.vugido.info.homeservicesadmin.models.homepage;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DATA{

	@JsonProperty("SLIDER")
	private List<SLIDERItem> sLIDER;

	@JsonProperty("SERVICE_CATEGORIES")
	private List<SERVICECATEGORIESItem> sERVICECATEGORIES;

	@JsonProperty("SERVICE_PRODUCTS")
	private List<SERVICEPRODUCTSItem> sERVICEPRODUCTS;

	public void setSLIDER(List<SLIDERItem> sLIDER){
		this.sLIDER = sLIDER;
	}

	public List<SLIDERItem> getSLIDER(){
		return sLIDER;
	}

	public void setSERVICECATEGORIES(List<SERVICECATEGORIESItem> sERVICECATEGORIES){
		this.sERVICECATEGORIES = sERVICECATEGORIES;
	}

	public List<SERVICECATEGORIESItem> getSERVICECATEGORIES(){
		return sERVICECATEGORIES;
	}

	public void setSERVICEPRODUCTS(List<SERVICEPRODUCTSItem> sERVICEPRODUCTS){
		this.sERVICEPRODUCTS = sERVICEPRODUCTS;
	}

	public List<SERVICEPRODUCTSItem> getSERVICEPRODUCTS(){
		return sERVICEPRODUCTS;
	}

	@Override
 	public String toString(){
		return 
			"DATA{" + 
			"sLIDER = '" + sLIDER + '\'' + 
			",sERVICE_CATEGORIES = '" + sERVICECATEGORIES + '\'' + 
			",sERVICE_PRODUCTS = '" + sERVICEPRODUCTS + '\'' + 
			"}";
		}
}
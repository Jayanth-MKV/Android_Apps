package com.vugido.info.homeservicesadmin.models.near_page;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DATA{

	@JsonProperty("NEAR_SERVICE_CATEGORIES")
	private List<NEARSERVICECATEGORIESItem> nEARSERVICECATEGORIES;

	@JsonProperty("NEAR_SERVICES")
	private List<NEARSERVICESItem> nEARSERVICES;

	public void setNEARSERVICECATEGORIES(List<NEARSERVICECATEGORIESItem> nEARSERVICECATEGORIES){
		this.nEARSERVICECATEGORIES = nEARSERVICECATEGORIES;
	}

	public List<NEARSERVICECATEGORIESItem> getNEARSERVICECATEGORIES(){
		return nEARSERVICECATEGORIES;
	}

	public void setNEARSERVICES(List<NEARSERVICESItem> nEARSERVICES){
		this.nEARSERVICES = nEARSERVICES;
	}

	public List<NEARSERVICESItem> getNEARSERVICES(){
		return nEARSERVICES;
	}

	@Override
 	public String toString(){
		return 
			"DATA{" + 
			"nEAR_SERVICE_CATEGORIES = '" + nEARSERVICECATEGORIES + '\'' + 
			",nEAR_SERVICES = '" + nEARSERVICES + '\'' + 
			"}";
		}
}
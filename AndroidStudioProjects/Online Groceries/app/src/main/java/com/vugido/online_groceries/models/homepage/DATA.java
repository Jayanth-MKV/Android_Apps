package com.vugido.online_groceries.models.homepage;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DATA{

	@JsonProperty("SLIDER")
	private List<SLIDERItem> sLIDER;

	@JsonProperty("SERVICE_TITLE")
	private String sERVICETITLE;

	public String getiMAGE() {
		return iMAGE;
	}

	public void setiMAGE(String iMAGE) {
		this.iMAGE = iMAGE;
	}

	@JsonProperty("IMAGE")
	private String iMAGE;

	@JsonProperty("SERVICE_CLIENTS")
	private List<SERVICECLIENTSItem> sERVICECLIENTS;

	@JsonProperty("SERVICE_CLIENTS_TYPE")
	private List<SERVICECLIENTSTYPEItem> sERVICECLIENTSTYPE;

	@JsonProperty("SERVICE_CATEGORIES")
	private List<SERVICECATEGORIESItem> sERVICECATEGORIES;

	@JsonProperty("SERVICE_DES")
	private String sERVICEDES;

	public void setSLIDER(List<SLIDERItem> sLIDER){
		this.sLIDER = sLIDER;
	}

	public List<SLIDERItem> getSLIDER(){
		return sLIDER;
	}

	public void setSERVICETITLE(String sERVICETITLE){
		this.sERVICETITLE = sERVICETITLE;
	}

	public String getSERVICETITLE(){
		return sERVICETITLE;
	}

	public void setSERVICECLIENTS(List<SERVICECLIENTSItem> sERVICECLIENTS){
		this.sERVICECLIENTS = sERVICECLIENTS;
	}

	public List<SERVICECLIENTSItem> getSERVICECLIENTS(){
		return sERVICECLIENTS;
	}

	public void setSERVICECLIENTSTYPE(List<SERVICECLIENTSTYPEItem> sERVICECLIENTSTYPE){
		this.sERVICECLIENTSTYPE = sERVICECLIENTSTYPE;
	}

	public List<SERVICECLIENTSTYPEItem> getSERVICECLIENTSTYPE(){
		return sERVICECLIENTSTYPE;
	}

	public void setSERVICECATEGORIES(List<SERVICECATEGORIESItem> sERVICECATEGORIES){
		this.sERVICECATEGORIES = sERVICECATEGORIES;
	}

	public List<SERVICECATEGORIESItem> getSERVICECATEGORIES(){
		return sERVICECATEGORIES;
	}

	public void setSERVICEDES(String sERVICEDES){
		this.sERVICEDES = sERVICEDES;
	}

	public String getSERVICEDES(){
		return sERVICEDES;
	}

	@Override
 	public String toString(){
		return 
			"DATA{" + 
			"sLIDER = '" + sLIDER + '\'' + 
			",sERVICE_TITLE = '" + sERVICETITLE + '\'' + 
			",sERVICE_CLIENTS = '" + sERVICECLIENTS + '\'' + 
			",sERVICE_CLIENTS_TYPE = '" + sERVICECLIENTSTYPE + '\'' + 
			",sERVICE_CATEGORIES = '" + sERVICECATEGORIES + '\'' + 
			",sERVICE_DES = '" + sERVICEDES + '\'' +
			",iMAGE = '" + iMAGE + '\'' +
					"}";
		}
}
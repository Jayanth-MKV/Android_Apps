package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.tracking;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATA{

	@JsonProperty("COMPLETED")
	private String cOMPLETED;

	@JsonProperty("PROCESSING")
	private String pROCESSING;

	@JsonProperty("PACKING")
	private String pACKING;

	@JsonProperty("DISPATCHED")
	private String dISPATCHED;

	@JsonProperty("PACKED")
	private String pACKED;

	public void setCOMPLETED(String cOMPLETED){
		this.cOMPLETED = cOMPLETED;
	}

	public String getCOMPLETED(){
		return cOMPLETED;
	}

	public void setPROCESSING(String pROCESSING){
		this.pROCESSING = pROCESSING;
	}

	public String getPROCESSING(){
		return pROCESSING;
	}

	public void setPACKING(String pACKING){
		this.pACKING = pACKING;
	}

	public String getPACKING(){
		return pACKING;
	}

	public void setDISPATCHED(String dISPATCHED){
		this.dISPATCHED = dISPATCHED;
	}

	public String getDISPATCHED(){
		return dISPATCHED;
	}

	public void setPACKED(String pACKED){
		this.pACKED = pACKED;
	}

	public String getPACKED(){
		return pACKED;
	}

	@Override
 	public String toString(){
		return 
			"DATA{" + 
			"cOMPLETED = '" + cOMPLETED + '\'' + 
			",pROCESSING = '" + pROCESSING + '\'' + 
			",pACKING = '" + pACKING + '\'' + 
			",dISPATCHED = '" + dISPATCHED + '\'' + 
			",pACKED = '" + pACKED + '\'' + 
			"}";
		}
}
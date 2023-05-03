package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.verify_response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response{

	@JsonProperty("PROFILE")
	private PROFILE pROFILE;

	@JsonProperty("ERROR")
	private boolean eRROR;

	public void setPROFILE(PROFILE pROFILE){
		this.pROFILE = pROFILE;
	}

	public PROFILE getPROFILE(){
		return pROFILE;
	}

	public void setERROR(boolean eRROR){
		this.eRROR = eRROR;
	}

	public boolean isERROR(){
		return eRROR;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"pROFILE = '" + pROFILE + '\'' + 
			",eRROR = '" + eRROR + '\'' + 
			"}";
		}
}
package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.AppUpdate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response{

	@JsonProperty("UPDATED")
	private boolean uPDATED;

	@JsonProperty("error")
	private boolean error;

	public void setUPDATED(boolean uPDATED){
		this.uPDATED = uPDATED;
	}

	public boolean isUPDATED(){
		return uPDATED;
	}

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
		return error;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"uPDATED = '" + uPDATED + '\'' + 
			",error = '" + error + '\'' + 
			"}";
		}
}
package com.vugido.vugidoupdate.models.status;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response{

	@JsonProperty("ERROR")
	private boolean eRROR;

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
			"eRROR = '" + eRROR + '\'' + 
			"}";
		}
}
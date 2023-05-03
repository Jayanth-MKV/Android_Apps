package com.vugido.ap.sklm.home_interiors.models.MAIN_MODEL;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response{

	@JsonProperty("STATUS")
	private boolean sTATUS;

	@JsonProperty("DATA")
	private DATA dATA;

	public void setSTATUS(boolean sTATUS){
		this.sTATUS = sTATUS;
	}

	public boolean isSTATUS(){
		return sTATUS;
	}

	public void setDATA(DATA dATA){
		this.dATA = dATA;
	}

	public DATA getDATA(){
		return dATA;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"sTATUS = '" + sTATUS + '\'' + 
			",dATA = '" + dATA + '\'' + 
			"}";
		}
}
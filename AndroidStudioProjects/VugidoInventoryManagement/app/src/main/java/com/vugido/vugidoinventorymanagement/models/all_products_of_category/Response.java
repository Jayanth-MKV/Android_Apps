package com.vugido.vugidoinventorymanagement.models.all_products_of_category;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Response{

	@JsonProperty("STATUS")
	private boolean sTATUS;

	@JsonProperty("DATA")
	private List<DATAItem> dATA;

	public void setSTATUS(boolean sTATUS){
		this.sTATUS = sTATUS;
	}

	public boolean isSTATUS(){
		return sTATUS;
	}

	public void setDATA(List<DATAItem> dATA){
		this.dATA = dATA;
	}

	public List<DATAItem> getDATA(){
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
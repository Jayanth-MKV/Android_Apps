package com.vugido.homeservices.models.near_page.images;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("IMAGES")
	private String iMAGES;

	public void setIMAGES(String iMAGES){
		this.iMAGES = iMAGES;
	}

	public String getIMAGES(){
		return iMAGES;
	}

	@Override
 	public String toString(){
		return 
			"DATAItem{" + 
			"iMAGES = '" + iMAGES + '\'' + 
			"}";
		}
}
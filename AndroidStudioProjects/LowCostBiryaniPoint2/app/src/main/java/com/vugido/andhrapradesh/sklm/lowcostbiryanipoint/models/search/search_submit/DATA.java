package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.search.search_submit;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DATA{

	@JsonProperty("DATA")
	private List<DATAItem> dATA;

	@JsonProperty("CATEGORY")
	private int cATEGORY;

	@JsonProperty("SEARCH_KEY")
	private String sEARCHKEY;

	public void setDATA(List<DATAItem> dATA){
		this.dATA = dATA;
	}

	public List<DATAItem> getDATA(){
		return dATA;
	}

	public void setCATEGORY(int cATEGORY){
		this.cATEGORY = cATEGORY;
	}

	public int getCATEGORY(){
		return cATEGORY;
	}

	public void setSEARCHKEY(String sEARCHKEY){
		this.sEARCHKEY = sEARCHKEY;
	}

	public String getSEARCHKEY(){
		return sEARCHKEY;
	}

	@Override
 	public String toString(){
		return 
			"DATA{" + 
			"dATA = '" + dATA + '\'' + 
			",cATEGORY = '" + cATEGORY + '\'' + 
			",sEARCH_KEY = '" + sEARCHKEY + '\'' + 
			"}";
		}
}
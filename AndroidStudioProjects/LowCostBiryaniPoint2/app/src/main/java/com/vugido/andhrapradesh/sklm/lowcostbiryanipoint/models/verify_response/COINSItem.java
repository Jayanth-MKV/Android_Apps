package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.verify_response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class COINSItem{

	@JsonProperty("COINS")
	private int cOINS;

	@JsonProperty("ACTIVATED")
	private int aCTIVATED;

	public void setCOINS(int cOINS){
		this.cOINS = cOINS;
	}

	public int getCOINS(){
		return cOINS;
	}

	public void setACTIVATED(int aCTIVATED){
		this.aCTIVATED = aCTIVATED;
	}

	public int getACTIVATED(){
		return aCTIVATED;
	}

	@Override
 	public String toString(){
		return 
			"COINSItem{" + 
			"cOINS = '" + cOINS + '\'' + 
			",aCTIVATED = '" + aCTIVATED + '\'' + 
			"}";
		}
}
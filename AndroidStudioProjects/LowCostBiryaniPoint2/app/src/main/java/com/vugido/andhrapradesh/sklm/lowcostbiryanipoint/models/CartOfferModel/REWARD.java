package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.CartOfferModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class REWARD{

	@JsonProperty("DISCOUNT")
	private int dISCOUNT;

	public void setDISCOUNT(int dISCOUNT){
		this.dISCOUNT = dISCOUNT;
	}

	public int getDISCOUNT(){
		return dISCOUNT;
	}

	@Override
 	public String toString(){
		return 
			"REWARD{" + 
			"dISCOUNT = '" + dISCOUNT + '\'' + 
			"}";
		}
}
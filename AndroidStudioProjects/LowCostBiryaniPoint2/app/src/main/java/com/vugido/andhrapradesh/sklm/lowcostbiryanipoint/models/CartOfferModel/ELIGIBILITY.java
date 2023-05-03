package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.CartOfferModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ELIGIBILITY{

	@JsonProperty("CATEGORY_ELIGIBILITY")
	private boolean cATEGORYELIGIBILITY;

	@JsonProperty("CART_ELIGIBILITY")
	private boolean cARTELIGIBILITY;

	public void setCATEGORYELIGIBILITY(boolean cATEGORYELIGIBILITY){
		this.cATEGORYELIGIBILITY = cATEGORYELIGIBILITY;
	}

	public boolean isCATEGORYELIGIBILITY(){
		return cATEGORYELIGIBILITY;
	}

	public void setCARTELIGIBILITY(boolean cARTELIGIBILITY){
		this.cARTELIGIBILITY = cARTELIGIBILITY;
	}

	public boolean isCARTELIGIBILITY(){
		return cARTELIGIBILITY;
	}

	@Override
 	public String toString(){
		return 
			"ELIGIBILITY{" + 
			"cATEGORY_ELIGIBILITY = '" + cATEGORYELIGIBILITY + '\'' + 
			",cART_ELIGIBILITY = '" + cARTELIGIBILITY + '\'' + 
			"}";
		}
}
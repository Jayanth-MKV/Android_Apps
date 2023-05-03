package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.CartOfferModel;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DATA{

	@JsonProperty("CATEGORY_DATA")
	private List<CATEGORYDATAItem> cATEGORYDATA;

	@JsonProperty("ELIGIBILITY")
	private ELIGIBILITY eLIGIBILITY;

	@JsonProperty("CART_DATA")
	private CARTDATA cARTDATA;

	public void setCATEGORYDATA(List<CATEGORYDATAItem> cATEGORYDATA){
		this.cATEGORYDATA = cATEGORYDATA;
	}

	public List<CATEGORYDATAItem> getCATEGORYDATA(){
		return cATEGORYDATA;
	}

	public void setELIGIBILITY(ELIGIBILITY eLIGIBILITY){
		this.eLIGIBILITY = eLIGIBILITY;
	}

	public ELIGIBILITY getELIGIBILITY(){
		return eLIGIBILITY;
	}

	public void setCARTDATA(CARTDATA cARTDATA){
		this.cARTDATA = cARTDATA;
	}

	public CARTDATA getCARTDATA(){
		return cARTDATA;
	}

	@Override
 	public String toString(){
		return 
			"DATA{" + 
			"cATEGORY_DATA = '" + cATEGORYDATA + '\'' + 
			",eLIGIBILITY = '" + eLIGIBILITY + '\'' + 
			",cART_DATA = '" + cARTDATA + '\'' + 
			"}";
		}
}
package com.vugido.online_groceries.models.orders.tracking;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATA{

	@JsonProperty("CANCELLED")
	private String cANCELLED;

	@JsonProperty("DELIVERED")
	private String dELIVERED;

	@JsonProperty("FAILED")
	private String fAILED;

	@JsonProperty("PACKING")
	private String pACKING;

	@JsonProperty("DISPATCHED")
	private String dISPATCHED;

	@JsonProperty("PACKED")
	private String pACKED;

	public void setCANCELLED(String cANCELLED){
		this.cANCELLED = cANCELLED;
	}

	public String getCANCELLED(){
		return cANCELLED;
	}

	public void setDELIVERED(String dELIVERED){
		this.dELIVERED = dELIVERED;
	}

	public String getDELIVERED(){
		return dELIVERED;
	}

	public void setFAILED(String fAILED){
		this.fAILED = fAILED;
	}

	public String getFAILED(){
		return fAILED;
	}

	public void setPACKING(String pACKING){
		this.pACKING = pACKING;
	}

	public String getPACKING(){
		return pACKING;
	}

	public void setDISPATCHED(String dISPATCHED){
		this.dISPATCHED = dISPATCHED;
	}

	public String getDISPATCHED(){
		return dISPATCHED;
	}

	public void setPACKED(String pACKED){
		this.pACKED = pACKED;
	}

	public String getPACKED(){
		return pACKED;
	}

	@Override
 	public String toString(){
		return 
			"DATA{" + 
			"cANCELLED = '" + cANCELLED + '\'' + 
			",dELIVERED = '" + dELIVERED + '\'' + 
			",fAILED = '" + fAILED + '\'' + 
			",pACKING = '" + pACKING + '\'' + 
			",dISPATCHED = '" + dISPATCHED + '\'' + 
			",pACKED = '" + pACKED + '\'' + 
			"}";
		}
}
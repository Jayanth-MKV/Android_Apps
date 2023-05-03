package com.foodhub.vugido.models.shipping;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATA{

	@JsonProperty("OTHERS")
	private String oTHERS;

	@JsonProperty("TODAY")
	private boolean tODAY;

	@JsonProperty("TODAY_DESCRIPTION")
	private String tODAYDESCRIPTION;

	@JsonProperty("TOMORROW")
	private boolean tOMORROW;

	@JsonProperty("TOMORROW_DESCRIPTION")
	private String tOMORROWDESCRIPTION;

	public void setOTHERS(String oTHERS){
		this.oTHERS = oTHERS;
	}

	public String getOTHERS(){
		return oTHERS;
	}

	public void setTODAY(boolean tODAY){
		this.tODAY = tODAY;
	}

	public boolean isTODAY(){
		return tODAY;
	}

	public void setTODAYDESCRIPTION(String tODAYDESCRIPTION){
		this.tODAYDESCRIPTION = tODAYDESCRIPTION;
	}

	public String getTODAYDESCRIPTION(){
		return tODAYDESCRIPTION;
	}

	public void setTOMORROW(boolean tOMORROW){
		this.tOMORROW = tOMORROW;
	}

	public boolean isTOMORROW(){
		return tOMORROW;
	}

	public void setTOMORROWDESCRIPTION(String tOMORROWDESCRIPTION){
		this.tOMORROWDESCRIPTION = tOMORROWDESCRIPTION;
	}

	public String getTOMORROWDESCRIPTION(){
		return tOMORROWDESCRIPTION;
	}

	@Override
 	public String toString(){
		return 
			"DATA{" + 
			"oTHERS = '" + oTHERS + '\'' + 
			",tODAY = '" + tODAY + '\'' + 
			",tODAY_DESCRIPTION = '" + tODAYDESCRIPTION + '\'' + 
			",tOMORROW = '" + tOMORROW + '\'' + 
			",tOMORROW_DESCRIPTION = '" + tOMORROWDESCRIPTION + '\'' + 
			"}";
		}
}
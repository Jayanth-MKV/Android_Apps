package com.foodhub.vugido.models.slots_data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response{

	@JsonProperty("TOMMOROW")
	private boolean tOMMOROW;

	@JsonProperty("STATUS")
	private boolean sTATUS;

	@JsonProperty("TOMMOROW_SLOTS")
	private TOMMOROWSLOTS tOMMOROWSLOTS;

	@JsonProperty("TODAY")
	private boolean tODAY;

	@JsonProperty("TODAY_SLOTS")
	private TODAYSLOTS tODAYSLOTS;

	@JsonProperty("DES")
	private String dES;


	public void setDES(String dES){
		this.dES = dES;
	}

	public String getDES(){
		return dES;
	}



	public void setTOMMOROW(boolean tOMMOROW){
		this.tOMMOROW = tOMMOROW;
	}

	public boolean isTOMMOROW(){
		return tOMMOROW;
	}

	public void setSTATUS(boolean sTATUS){
		this.sTATUS = sTATUS;
	}

	public boolean isSTATUS(){
		return sTATUS;
	}

	public void setTOMMOROWSLOTS(TOMMOROWSLOTS tOMMOROWSLOTS){
		this.tOMMOROWSLOTS = tOMMOROWSLOTS;
	}

	public TOMMOROWSLOTS getTOMMOROWSLOTS(){
		return tOMMOROWSLOTS;
	}

	public void setTODAY(boolean tODAY){
		this.tODAY = tODAY;
	}

	public boolean isTODAY(){
		return tODAY;
	}

	public void setTODAYSLOTS(TODAYSLOTS tODAYSLOTS){
		this.tODAYSLOTS = tODAYSLOTS;
	}

	public TODAYSLOTS getTODAYSLOTS(){
		return tODAYSLOTS;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"tOMMOROW = '" + tOMMOROW + '\'' + 
			",sTATUS = '" + sTATUS + '\'' + 
			",tOMMOROW_SLOTS = '" + tOMMOROWSLOTS + '\'' + 
			",tODAY = '" + tODAY + '\'' + 
			",tODAY_SLOTS = '" + tODAYSLOTS + '\'' +
					",dES = '" + dES + '\'' +
					"}";
		}
}
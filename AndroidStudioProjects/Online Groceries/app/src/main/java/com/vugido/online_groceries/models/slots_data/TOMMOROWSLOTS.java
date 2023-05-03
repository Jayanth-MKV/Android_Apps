package com.vugido.online_groceries.models.slots_data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TOMMOROWSLOTS{

	@JsonProperty("SLOTS")
	private List<SLOTSItem> sLOTS;

	@JsonProperty("DESCRIPTION")
	private String dESCRIPTION;

	public void setSLOTS(List<SLOTSItem> sLOTS){
		this.sLOTS = sLOTS;
	}

	public List<SLOTSItem> getSLOTS(){
		return sLOTS;
	}

	public void setDESCRIPTION(String dESCRIPTION){
		this.dESCRIPTION = dESCRIPTION;
	}

	public String getDESCRIPTION(){
		return dESCRIPTION;
	}

	@Override
 	public String toString(){
		return 
			"TOMMOROWSLOTS{" + 
			"sLOTS = '" + sLOTS + '\'' + 
			",dESCRIPTION = '" + dESCRIPTION + '\'' + 
			"}";
		}
}
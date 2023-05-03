package com.vugido.online_groceries.models.slots_data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SLOTSItem{

	@JsonProperty("SLOT")
	private String sLOT;

	@JsonProperty("SLOT_ID")
	private int sLOTID;

	public void setSLOT(String sLOT){
		this.sLOT = sLOT;
	}

	public String getSLOT(){
		return sLOT;
	}

	public void setSLOTID(int sLOTID){
		this.sLOTID = sLOTID;
	}

	public int getSLOTID(){
		return sLOTID;
	}

	@Override
 	public String toString(){
		return 
			"SLOTSItem{" + 
			"sLOT = '" + sLOT + '\'' + 
			",sLOT_ID = '" + sLOTID + '\'' + 
			"}";
		}
}
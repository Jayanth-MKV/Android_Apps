package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.updated.slots_data;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TODAYSLOTS{

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
			"TODAYSLOTS{" + 
			"sLOTS = '" + sLOTS + '\'' + 
			",dESCRIPTION = '" + dESCRIPTION + '\'' + 
			"}";
		}
}
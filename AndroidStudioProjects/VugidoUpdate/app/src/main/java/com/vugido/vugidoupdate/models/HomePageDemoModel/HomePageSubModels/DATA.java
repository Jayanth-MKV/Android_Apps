package com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DATA{

	@JsonProperty("STATUS")
	private boolean sTATUS;

	@JsonProperty("DATA_ITEM")
	private List<DATAITEMItem> dATAITEM;

	public void setSTATUS(boolean sTATUS){
		this.sTATUS = sTATUS;
	}

	public boolean isSTATUS(){
		return sTATUS;
	}

	public void setDATAITEM(List<DATAITEMItem> dATAITEM){
		this.dATAITEM = dATAITEM;
	}

	public List<DATAITEMItem> getDATAITEM(){
		return dATAITEM;
	}

	@Override
 	public String toString(){
		return 
			"DATA{" + 
			"sTATUS = '" + sTATUS + '\'' + 
			",dATA_ITEM = '" + dATAITEM + '\'' + 
			"}";
		}
}
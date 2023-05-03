package com.foodhub.vugido.models.scratch_cards;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Data{

	@JsonProperty("SCRATCH_CARDS")
	private List<SCRATCHCARDSItem> sCRATCHCARDS;

	@JsonProperty("COINS")
	private int cOINS;

	public void setSCRATCHCARDS(List<SCRATCHCARDSItem> sCRATCHCARDS){
		this.sCRATCHCARDS = sCRATCHCARDS;
	}

	public List<SCRATCHCARDSItem> getSCRATCHCARDS(){
		return sCRATCHCARDS;
	}

	public void setCOINS(int cOINS){
		this.cOINS = cOINS;
	}

	public int getCOINS(){
		return cOINS;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"sCRATCH_CARDS = '" + sCRATCHCARDS + '\'' + 
			",cOINS = '" + cOINS + '\'' + 
			"}";
		}
}
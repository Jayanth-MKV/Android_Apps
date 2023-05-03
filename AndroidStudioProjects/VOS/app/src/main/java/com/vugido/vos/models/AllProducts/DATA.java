package com.vugido.vos.models.AllProducts;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DATA{

	@JsonProperty("MENU")
	private List<MENUItem> mENU;

	@JsonProperty("ITEMS")
	private List<ITEMSItem> iTEMS;

	public void setMENU(List<MENUItem> mENU){
		this.mENU = mENU;
	}

	public List<MENUItem> getMENU(){
		return mENU;
	}

	public void setITEMS(List<ITEMSItem> iTEMS){
		this.iTEMS = iTEMS;
	}

	public List<ITEMSItem> getITEMS(){
		return iTEMS;
	}

	@Override
 	public String toString(){
		return 
			"DATA{" + 
			"mENU = '" + mENU + '\'' + 
			",iTEMS = '" + iTEMS + '\'' + 
			"}";
		}
}
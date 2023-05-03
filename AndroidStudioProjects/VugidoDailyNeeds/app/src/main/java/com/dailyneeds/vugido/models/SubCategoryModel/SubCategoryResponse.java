package com.dailyneeds.vugido.models.SubCategoryModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class SubCategoryResponse{

	@JsonProperty("MENU")
	private List<SubMenuItem> mENU;

	@JsonProperty("ITEMS")
	private List<SubCategoryItem> iTEMS;

	public void setMENU(List<SubMenuItem> mENU){
		this.mENU = mENU;
	}

	public List<SubMenuItem> getMENU(){
		return mENU;
	}

	public void setITEMS(List<SubCategoryItem> iTEMS){
		this.iTEMS = iTEMS;
	}

	public List<SubCategoryItem> getITEMS(){
		return iTEMS;
	}

	@Override
 	public String toString(){
		return 
			"SubCategoryResponse{" + 
			"mENU = '" + mENU + '\'' + 
			",iTEMS = '" + iTEMS + '\'' + 
			"}";
		}
}
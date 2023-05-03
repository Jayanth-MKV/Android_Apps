package com.vugido_business_panel.models.Categories.mainCategories;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("ACTIVE_STATE")
	private int aCTIVESTATE;

	@JsonProperty("CATEGORY")
	private String cATEGORY;

	@JsonProperty("ID")
	private int iD;

	@JsonProperty("ICONS")
	private String iCONS;

	public void setACTIVESTATE(int aCTIVESTATE){
		this.aCTIVESTATE = aCTIVESTATE;
	}

	public int getACTIVESTATE(){
		return aCTIVESTATE;
	}

	public void setCATEGORY(String cATEGORY){
		this.cATEGORY = cATEGORY;
	}

	public String getCATEGORY(){
		return cATEGORY;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setICONS(String iCONS){
		this.iCONS = iCONS;
	}

	public String getICONS(){
		return iCONS;
	}

	@Override
 	public String toString(){
		return 
			"DATAItem{" + 
			"aCTIVE_STATE = '" + aCTIVESTATE + '\'' + 
			",cATEGORY = '" + cATEGORY + '\'' + 
			",iD = '" + iD + '\'' + 
			",iCONS = '" + iCONS + '\'' + 
			"}";
		}
}
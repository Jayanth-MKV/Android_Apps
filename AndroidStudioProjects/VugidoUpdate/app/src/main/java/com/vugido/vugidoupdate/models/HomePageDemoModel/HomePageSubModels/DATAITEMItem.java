package com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAITEMItem{

	@JsonProperty("VID")
	private int vID;

	@JsonProperty("BG_COLOR")
	private String bGCOLOR;

	@JsonProperty("HID")
	private int hID;

	@JsonProperty("TITLE_COLOR")
	private String tITLECOLOR;

	@JsonProperty("TITLE")
	private String tITLE;

	@JsonProperty("VIEW_MORE")
	private int vIEWMORE;

	public void setVID(int vID){
		this.vID = vID;
	}

	public int getVID(){
		return vID;
	}

	public void setBGCOLOR(String bGCOLOR){
		this.bGCOLOR = bGCOLOR;
	}

	public String getBGCOLOR(){
		return bGCOLOR;
	}

	public void setHID(int hID){
		this.hID = hID;
	}

	public int getHID(){
		return hID;
	}

	public void setTITLECOLOR(String tITLECOLOR){
		this.tITLECOLOR = tITLECOLOR;
	}

	public String getTITLECOLOR(){
		return tITLECOLOR;
	}

	public void setTITLE(String tITLE){
		this.tITLE = tITLE;
	}

	public String getTITLE(){
		return tITLE;
	}

	public void setVIEWMORE(int vIEWMORE){
		this.vIEWMORE = vIEWMORE;
	}

	public int getVIEWMORE(){
		return vIEWMORE;
	}

	@Override
 	public String toString(){
		return 
			"DATAITEMItem{" + 
			"vID = '" + vID + '\'' + 
			",bG_COLOR = '" + bGCOLOR + '\'' + 
			",hID = '" + hID + '\'' + 
			",tITLE_COLOR = '" + tITLECOLOR + '\'' + 
			",tITLE = '" + tITLE + '\'' + 
			",vIEW_MORE = '" + vIEWMORE + '\'' + 
			"}";
		}
}
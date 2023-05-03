package com.vugido.info.homeservicesadmin.models.near_page;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NEARSERVICECATEGORIESItem{

	@JsonProperty("TITLE")
	private String tITLE;

	@JsonProperty("ID")
	private int iD;

	public void setTITLE(String tITLE){
		this.tITLE = tITLE;
	}

	public String getTITLE(){
		return tITLE;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	@Override
 	public String toString(){
		return 
			"NEARSERVICECATEGORIESItem{" + 
			"tITLE = '" + tITLE + '\'' + 
			",iD = '" + iD + '\'' + 
			"}";
		}
}
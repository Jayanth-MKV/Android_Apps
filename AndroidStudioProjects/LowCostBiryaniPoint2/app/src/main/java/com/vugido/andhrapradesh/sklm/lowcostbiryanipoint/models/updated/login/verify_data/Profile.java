package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.updated.login.verify_data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Profile{

	@JsonProperty("UID")
	private int uID;

	@JsonProperty("RC")
	private String rC;

	@JsonProperty("mobile")
	private String mobile;

	@JsonProperty("FU")
	private int fU;

	public void setUID(int uID){
		this.uID = uID;
	}

	public int getUID(){
		return uID;
	}

	public void setRC(String rC){
		this.rC = rC;
	}

	public String getRC(){
		return rC;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return mobile;
	}

	public void setFU(int fU){
		this.fU = fU;
	}

	public int getFU(){
		return fU;
	}

	@Override
 	public String toString(){
		return 
			"Profile{" + 
			"uID = '" + uID + '\'' + 
			",rC = '" + rC + '\'' + 
			",mobile = '" + mobile + '\'' + 
			",fU = '" + fU + '\'' + 
			"}";
		}
}
package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.VEG_HOME_PAGE_MODEL;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class OFFERSItem{

	@JsonProperty("IMAGE")
	private String iMAGE;

	@JsonProperty("BG")
	private String bG;

	@JsonProperty("ID")
	private int iD;

	public void setIMAGE(String iMAGE){
		this.iMAGE = iMAGE;
	}

	public String getIMAGE(){
		return iMAGE;
	}

	public void setBG(String bG){
		this.bG = bG;
	}

	public String getBG(){
		return bG;
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
			"OFFERSItem{" + 
			"iMAGE = '" + iMAGE + '\'' + 
			",bG = '" + bG + '\'' + 
			",iD = '" + iD + '\'' + 
			"}";
		}
}
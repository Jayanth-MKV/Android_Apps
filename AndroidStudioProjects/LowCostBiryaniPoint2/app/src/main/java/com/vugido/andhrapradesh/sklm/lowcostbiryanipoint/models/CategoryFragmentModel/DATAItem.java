package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.CategoryFragmentModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class DATAItem{

	@JsonProperty("CAT_TITLE")
	private String cATTITLE;

	@JsonProperty("ID")
	private int iD;

	@JsonProperty("ICON")
	private String iCON;

	public void setCATTITLE(String cATTITLE){
		this.cATTITLE = cATTITLE;
	}

	public String getCATTITLE(){
		return cATTITLE;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setICON(String iCON){
		this.iCON = iCON;
	}

	public String getICON(){
		return iCON;
	}

	@Override
 	public String toString(){
		return 
			"DATAItem{" + 
			"cAT_TITLE = '" + cATTITLE + '\'' + 
			",iD = '" + iD + '\'' + 
			",iCON = '" + iCON + '\'' + 
			"}";
		}
}
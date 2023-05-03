package com.foodhub.vugido.models.scratch_cards;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SCRATCHCARDSItem{

	@JsonProperty("IS_REVEALED")
	private int iSREVEALED;

	@JsonProperty("SCRATCH_ID")
	private int sCRATCHID;

	@JsonProperty("VALUE")
	private int vALUE;

	@JsonProperty("DATED")
	private String dATED;

	@JsonProperty("OID")
	private int oID;

	@JsonProperty("REASON")
	private String rEASON;

	public void setISREVEALED(int iSREVEALED){
		this.iSREVEALED = iSREVEALED;
	}

	public int getISREVEALED(){
		return iSREVEALED;
	}

	public void setSCRATCHID(int sCRATCHID){
		this.sCRATCHID = sCRATCHID;
	}

	public int getSCRATCHID(){
		return sCRATCHID;
	}

	public void setVALUE(int vALUE){
		this.vALUE = vALUE;
	}

	public int getVALUE(){
		return vALUE;
	}

	public void setDATED(String dATED){
		this.dATED = dATED;
	}

	public String getDATED(){
		return dATED;
	}

	public void setOID(int oID){
		this.oID = oID;
	}

	public int getOID(){
		return oID;
	}

	public void setREASON(String rEASON){
		this.rEASON = rEASON;
	}

	public String getREASON(){
		return rEASON;
	}

	@Override
 	public String toString(){
		return 
			"SCRATCHCARDSItem{" + 
			"iS_REVEALED = '" + iSREVEALED + '\'' + 
			",sCRATCH_ID = '" + sCRATCHID + '\'' + 
			",vALUE = '" + vALUE + '\'' + 
			",dATED = '" + dATED + '\'' + 
			",oID = '" + oID + '\'' + 
			",rEASON = '" + rEASON + '\'' + 
			"}";
		}
}
package com.vugido_business_panel.models.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("UID")
	private int uID;

	@JsonProperty("STATUS")
	private int sTATUS;

	@JsonProperty("TNO")
	private int tNO;

	@JsonProperty("IC")
	private int iC;

	@JsonProperty("OID")
	private int oID;

	@JsonProperty("DATED")
	private String dATED;

	@JsonProperty("TP")
	private int tP;

	@JsonProperty("PM")
	private int pM;

	@JsonProperty("OM")
	private int oM;

	@JsonProperty("CID")
	private int cID;

	public void setUID(int uID){
		this.uID = uID;
	}

	public int getUID(){
		return uID;
	}

	public void setSTATUS(int sTATUS){
		this.sTATUS = sTATUS;
	}

	public int getSTATUS(){
		return sTATUS;
	}

	public void setTNO(int tNO){
		this.tNO = tNO;
	}

	public int getTNO(){
		return tNO;
	}

	public void setIC(int iC){
		this.iC = iC;
	}

	public int getIC(){
		return iC;
	}

	public void setOID(int oID){
		this.oID = oID;
	}

	public int getOID(){
		return oID;
	}

	public void setDATED(String dATED){
		this.dATED = dATED;
	}

	public String getDATED(){
		return dATED;
	}

	public void setTP(int tP){
		this.tP = tP;
	}

	public int getTP(){
		return tP;
	}

	public void setPM(int pM){
		this.pM = pM;
	}

	public int getPM(){
		return pM;
	}

	public void setOM(int oM){
		this.oM = oM;
	}

	public int getOM(){
		return oM;
	}

	public void setCID(int cID){
		this.cID = cID;
	}

	public int getCID(){
		return cID;
	}

	@Override
 	public String toString(){
		return 
			"DATAItem{" + 
			"uID = '" + uID + '\'' + 
			",sTATUS = '" + sTATUS + '\'' + 
			",tNO = '" + tNO + '\'' + 
			",iC = '" + iC + '\'' + 
			",oID = '" + oID + '\'' + 
			",dATED = '" + dATED + '\'' + 
			",tP = '" + tP + '\'' + 
			",pM = '" + pM + '\'' + 
			",oM = '" + oM + '\'' + 
			",cID = '" + cID + '\'' + 
			"}";
		}
}
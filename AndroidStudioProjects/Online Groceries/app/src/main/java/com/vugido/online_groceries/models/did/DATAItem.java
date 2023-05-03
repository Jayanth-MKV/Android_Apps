package com.vugido.online_groceries.models.did;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("STATUS")
	private int sTATUS;

	@JsonProperty("ASSIGNED_ON")
	private String aSSIGNEDON;

	@JsonProperty("LAN")
	private String lAN;

	@JsonProperty("LON")
	private String lON;

	@JsonProperty("ID")
	private int iD;

	@JsonProperty("OID")
	private int oID;

	@JsonProperty("DID")
	private int dID;

	@JsonProperty("CLOSED_ON")
	private String cLOSEDON;

	public void setSTATUS(int sTATUS){
		this.sTATUS = sTATUS;
	}

	public int getSTATUS(){
		return sTATUS;
	}

	public void setASSIGNEDON(String aSSIGNEDON){
		this.aSSIGNEDON = aSSIGNEDON;
	}

	public String getASSIGNEDON(){
		return aSSIGNEDON;
	}

	public void setLAN(String lAN){
		this.lAN = lAN;
	}

	public String getLAN(){
		return lAN;
	}

	public void setLON(String lON){
		this.lON = lON;
	}

	public String getLON(){
		return lON;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setOID(int oID){
		this.oID = oID;
	}

	public int getOID(){
		return oID;
	}

	public void setDID(int dID){
		this.dID = dID;
	}

	public int getDID(){
		return dID;
	}

	public void setCLOSEDON(String cLOSEDON){
		this.cLOSEDON = cLOSEDON;
	}

	public String getCLOSEDON(){
		return cLOSEDON;
	}

	@Override
 	public String toString(){
		return 
			"DATAItem{" + 
			"sTATUS = '" + sTATUS + '\'' + 
			",aSSIGNED_ON = '" + aSSIGNEDON + '\'' + 
			",lAN = '" + lAN + '\'' + 
			",lON = '" + lON + '\'' + 
			",iD = '" + iD + '\'' + 
			",oID = '" + oID + '\'' + 
			",dID = '" + dID + '\'' + 
			",cLOSED_ON = '" + cLOSEDON + '\'' + 
			"}";
		}
}
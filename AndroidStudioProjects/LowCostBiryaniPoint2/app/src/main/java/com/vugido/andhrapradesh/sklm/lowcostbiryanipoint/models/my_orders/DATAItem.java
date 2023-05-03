package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.my_orders;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("UID")
	private int uID;

	@JsonProperty("ITEMS_PRICE")
	private float iTEMSPRICE;

	@JsonProperty("STATUS")
	private int sTATUS;

	@JsonProperty("DELIVER_DATE")
	private String dELIVERDATE;

	@JsonProperty("TIME")
	private int tIME;

	@JsonProperty("OID")
	private int oID;

	@JsonProperty("ITEM_COUNT")
	private int iTEMCOUNT;

	@JsonProperty("DATED")
	private String dATED;

	@JsonProperty("AID")
	private int aID;

	@JsonProperty("IMAGES_ARRAY")
	private List<String> iMAGESARRAY;

	@JsonProperty("DC")
	private float dC;

	public void setUID(int uID){
		this.uID = uID;
	}

	public int getUID(){
		return uID;
	}

	public void setITEMSPRICE(float iTEMSPRICE){
		this.iTEMSPRICE = iTEMSPRICE;
	}

	public float getITEMSPRICE(){
		return iTEMSPRICE;
	}

	public void setSTATUS(int sTATUS){
		this.sTATUS = sTATUS;
	}

	public int getSTATUS(){
		return sTATUS;
	}

	public void setDELIVERDATE(String dELIVERDATE){
		this.dELIVERDATE = dELIVERDATE;
	}

	public String getDELIVERDATE(){
		return dELIVERDATE;
	}

	public void setTIME(int tIME){
		this.tIME = tIME;
	}

	public int getTIME(){
		return tIME;
	}

	public void setOID(int oID){
		this.oID = oID;
	}

	public int getOID(){
		return oID;
	}

	public void setITEMCOUNT(int iTEMCOUNT){
		this.iTEMCOUNT = iTEMCOUNT;
	}

	public int getITEMCOUNT(){
		return iTEMCOUNT;
	}

	public void setDATED(String dATED){
		this.dATED = dATED;
	}

	public String getDATED(){
		return dATED;
	}

	public void setAID(int aID){
		this.aID = aID;
	}

	public int getAID(){
		return aID;
	}

	public void setIMAGESARRAY(List<String> iMAGESARRAY){
		this.iMAGESARRAY = iMAGESARRAY;
	}

	public List<String> getIMAGESARRAY(){
		return iMAGESARRAY;
	}

	public void setDC(float dC){
		this.dC = dC;
	}

	public float getDC(){
		return dC;
	}

	@Override
 	public String toString(){
		return 
			"DATAItem{" + 
			"uID = '" + uID + '\'' + 
			",iTEMS_PRICE = '" + iTEMSPRICE + '\'' + 
			",sTATUS = '" + sTATUS + '\'' + 
			",dELIVER_DATE = '" + dELIVERDATE + '\'' + 
			",tIME = '" + tIME + '\'' + 
			",oID = '" + oID + '\'' + 
			",iTEM_COUNT = '" + iTEMCOUNT + '\'' + 
			",dATED = '" + dATED + '\'' + 
			",aID = '" + aID + '\'' + 
			",iMAGES_ARRAY = '" + iMAGESARRAY + '\'' + 
			",dC = '" + dC + '\'' + 
			"}";
		}
}
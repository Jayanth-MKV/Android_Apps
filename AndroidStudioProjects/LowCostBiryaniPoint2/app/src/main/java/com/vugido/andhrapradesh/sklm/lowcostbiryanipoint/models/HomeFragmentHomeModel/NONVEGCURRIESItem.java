package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.HomeFragmentHomeModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class NONVEGCURRIESItem{

	@JsonProperty("IMAGE")
	private String iMAGE;

	@JsonProperty("PRICE")
	private int pRICE;

	@JsonProperty("OFFER")
	private int oFFER;

	@JsonProperty("OFFER_STATUS")
	private int oFFERSTATUS;

	@JsonProperty("E_NAME")
	private String eNAME;

	@JsonProperty("PID")
	private int pID;

	@JsonProperty("UNIT_INTERVAL")
	private int uNITINTERVAL;

	@JsonProperty("IN_STOCK")
	private int iNSTOCK;

	@JsonProperty("SID")
	private int sID;

	public void setIMAGE(String iMAGE){
		this.iMAGE = iMAGE;
	}

	public String getIMAGE(){
		return iMAGE;
	}

	public void setPRICE(int pRICE){
		this.pRICE = pRICE;
	}

	public int getPRICE(){
		return pRICE;
	}

	public void setOFFER(int oFFER){
		this.oFFER = oFFER;
	}

	public int getOFFER(){
		return oFFER;
	}

	public void setOFFERSTATUS(int oFFERSTATUS){
		this.oFFERSTATUS = oFFERSTATUS;
	}

	public int getOFFERSTATUS(){
		return oFFERSTATUS;
	}

	public void setENAME(String eNAME){
		this.eNAME = eNAME;
	}

	public String getENAME(){
		return eNAME;
	}

	public void setPID(int pID){
		this.pID = pID;
	}

	public int getPID(){
		return pID;
	}

	public void setUNITINTERVAL(int uNITINTERVAL){
		this.uNITINTERVAL = uNITINTERVAL;
	}

	public int getUNITINTERVAL(){
		return uNITINTERVAL;
	}

	public void setINSTOCK(int iNSTOCK){
		this.iNSTOCK = iNSTOCK;
	}

	public int getINSTOCK(){
		return iNSTOCK;
	}

	public void setSID(int sID){
		this.sID = sID;
	}

	public int getSID(){
		return sID;
	}

	@Override
 	public String toString(){
		return 
			"NONVEGCURRIESItem{" + 
			"iMAGE = '" + iMAGE + '\'' + 
			",pRICE = '" + pRICE + '\'' + 
			",oFFER = '" + oFFER + '\'' + 
			",oFFER_STATUS = '" + oFFERSTATUS + '\'' + 
			",e_NAME = '" + eNAME + '\'' + 
			",pID = '" + pID + '\'' + 
			",uNIT_INTERVAL = '" + uNITINTERVAL + '\'' + 
			",iN_STOCK = '" + iNSTOCK + '\'' + 
			",sID = '" + sID + '\'' + 
			"}";
		}
}
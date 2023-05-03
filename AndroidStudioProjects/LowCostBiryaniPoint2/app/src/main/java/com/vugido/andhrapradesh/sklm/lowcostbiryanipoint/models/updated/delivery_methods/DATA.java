package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.updated.delivery_methods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATA{

	@JsonProperty("EXPRESS_DESCRIPTION")
	private String eXPRESSDESCRIPTION;

	@JsonProperty("EXPRESS")
	private boolean eXPRESS;

	@JsonProperty("EXPRESS_CHARGES")
	private int eXPRESSCHARGES;

	@JsonProperty("SLOT")
	private boolean sLOT;

	@JsonProperty("DES")
	private String dES;


	public void setDES(String dES){
		this.dES = dES;
	}

	public String getDES(){
		return dES;
	}


	@JsonProperty("SLOT_DESCRIPTION")
	private String sLOTDESCRIPTION;

	@JsonProperty("SLOT_CHARGES")
	private int sLOTCHARGES;

	public void setEXPRESSDESCRIPTION(String eXPRESSDESCRIPTION){
		this.eXPRESSDESCRIPTION = eXPRESSDESCRIPTION;
	}

	public String getEXPRESSDESCRIPTION(){
		return eXPRESSDESCRIPTION;
	}

	public void setEXPRESS(boolean eXPRESS){
		this.eXPRESS = eXPRESS;
	}

	public boolean isEXPRESS(){
		return eXPRESS;
	}

	public void setEXPRESSCHARGES(int eXPRESSCHARGES){
		this.eXPRESSCHARGES = eXPRESSCHARGES;
	}

	public int getEXPRESSCHARGES(){
		return eXPRESSCHARGES;
	}

	public void setSLOT(boolean sLOT){
		this.sLOT = sLOT;
	}

	public boolean isSLOT(){
		return sLOT;
	}

	public void setSLOTDESCRIPTION(String sLOTDESCRIPTION){
		this.sLOTDESCRIPTION = sLOTDESCRIPTION;
	}

	public String getSLOTDESCRIPTION(){
		return sLOTDESCRIPTION;
	}

	public void setSLOTCHARGES(int sLOTCHARGES){
		this.sLOTCHARGES = sLOTCHARGES;
	}

	public int getSLOTCHARGES(){
		return sLOTCHARGES;
	}

	@Override
 	public String toString(){
		return 
			"DATA{" + 
			"eXPRESS_DESCRIPTION = '" + eXPRESSDESCRIPTION + '\'' + 
			",eXPRESS = '" + eXPRESS + '\'' + 
			",eXPRESS_CHARGES = '" + eXPRESSCHARGES + '\'' + 
			",sLOT = '" + sLOT + '\'' + 
			",sLOT_DESCRIPTION = '" + sLOTDESCRIPTION + '\'' + 
			",sLOT_CHARGES = '" + sLOTCHARGES + '\'' +
					",dES = '" + dES + '\'' +
			"}";
		}
}
package vugido.foodhub.ap.sklm.hungrybirds.models.app_info;

import com.fasterxml.jackson.annotation.JsonProperty;

public class APPINFO{

	@JsonProperty("SERVICE_MSG")
	private String sERVICEMSG;

	@JsonProperty("DISTANCE_SERVED")
	private int dISTANCESERVED;

	@JsonProperty("APP_ON")
	private int aPPON;

	@JsonProperty("DELIVERY_APPLIED_FROM")
	private int dELIVERYAPPLIEDFROM;

	@JsonProperty("SERVICE_FROM")
	private String sERVICEFROM;

	@JsonProperty("DELIVERY_CHARGES")
	private int dELIVERYCHARGES;

	@JsonProperty("SERVICE_TILL")
	private String sERVICETILL;

	@JsonProperty("DELIVERY_TIME")
	private String dELIVERYTIME;

	public void setSERVICEMSG(String sERVICEMSG){
		this.sERVICEMSG = sERVICEMSG;
	}

	public String getSERVICEMSG(){
		return sERVICEMSG;
	}

	public void setDISTANCESERVED(int dISTANCESERVED){
		this.dISTANCESERVED = dISTANCESERVED;
	}

	public int getDISTANCESERVED(){
		return dISTANCESERVED;
	}

	public void setAPPON(int aPPON){
		this.aPPON = aPPON;
	}

	public int getAPPON(){
		return aPPON;
	}

	public void setDELIVERYAPPLIEDFROM(int dELIVERYAPPLIEDFROM){
		this.dELIVERYAPPLIEDFROM = dELIVERYAPPLIEDFROM;
	}

	public int getDELIVERYAPPLIEDFROM(){
		return dELIVERYAPPLIEDFROM;
	}

	public void setSERVICEFROM(String sERVICEFROM){
		this.sERVICEFROM = sERVICEFROM;
	}

	public String getSERVICEFROM(){
		return sERVICEFROM;
	}

	public void setDELIVERYCHARGES(int dELIVERYCHARGES){
		this.dELIVERYCHARGES = dELIVERYCHARGES;
	}

	public int getDELIVERYCHARGES(){
		return dELIVERYCHARGES;
	}

	public void setSERVICETILL(String sERVICETILL){
		this.sERVICETILL = sERVICETILL;
	}

	public String getSERVICETILL(){
		return sERVICETILL;
	}

	public void setDELIVERYTIME(String dELIVERYTIME){
		this.dELIVERYTIME = dELIVERYTIME;
	}

	public String getDELIVERYTIME(){
		return dELIVERYTIME;
	}

	@Override
 	public String toString(){
		return 
			"APPINFO{" + 
			"sERVICE_MSG = '" + sERVICEMSG + '\'' + 
			",dISTANCE_SERVED = '" + dISTANCESERVED + '\'' + 
			",aPP_ON = '" + aPPON + '\'' + 
			",dELIVERY_APPLIED_FROM = '" + dELIVERYAPPLIEDFROM + '\'' + 
			",sERVICE_FROM = '" + sERVICEFROM + '\'' + 
			",dELIVERY_CHARGES = '" + dELIVERYCHARGES + '\'' + 
			",sERVICE_TILL = '" + sERVICETILL + '\'' + 
			",dELIVERY_TIME = '" + dELIVERYTIME + '\'' + 
			"}";
		}
}
package vugido.foodhub.ap.sklm.hungrybirds.models.app_info;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response{

	@JsonProperty("STATUS")
	private boolean sTATUS;

	@JsonProperty("APP_INFO")
	private APPINFO aPPINFO;

	public void setSTATUS(boolean sTATUS){
		this.sTATUS = sTATUS;
	}

	public boolean isSTATUS(){
		return sTATUS;
	}

	public void setAPPINFO(APPINFO aPPINFO){
		this.aPPINFO = aPPINFO;
	}

	public APPINFO getAPPINFO(){
		return aPPINFO;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"sTATUS = '" + sTATUS + '\'' + 
			",aPP_INFO = '" + aPPINFO + '\'' + 
			"}";
		}
}
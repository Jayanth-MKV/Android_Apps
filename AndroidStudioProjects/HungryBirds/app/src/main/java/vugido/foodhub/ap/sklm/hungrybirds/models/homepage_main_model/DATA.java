package vugido.foodhub.ap.sklm.hungrybirds.models.homepage_main_model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DATA{

	@JsonProperty("SEC")
	private List<SECItem> sEC;

	@JsonProperty("QUOTE")
	private String qUOTE;

	@JsonProperty("HIGHT_LIGHT")
	private List<HIGHTLIGHTItem> hIGHTLIGHT;

	@JsonProperty("PRODUCTS_MENU")
	private List<PRODUCTSMENUItem> pRODUCTSMENU;

	@JsonProperty("CATEGORIES")
	private List<CATEGORIESItem> cATEGORIES;

	public void setSEC(List<SECItem> sEC){
		this.sEC = sEC;
	}

	public List<SECItem> getSEC(){
		return sEC;
	}

	public void setQUOTE(String qUOTE){
		this.qUOTE = qUOTE;
	}

	public String getQUOTE(){
		return qUOTE;
	}

	public void setHIGHTLIGHT(List<HIGHTLIGHTItem> hIGHTLIGHT){
		this.hIGHTLIGHT = hIGHTLIGHT;
	}

	public List<HIGHTLIGHTItem> getHIGHTLIGHT(){
		return hIGHTLIGHT;
	}

	public void setPRODUCTSMENU(List<PRODUCTSMENUItem> pRODUCTSMENU){
		this.pRODUCTSMENU = pRODUCTSMENU;
	}

	public List<PRODUCTSMENUItem> getPRODUCTSMENU(){
		return pRODUCTSMENU;
	}

	public void setCATEGORIES(List<CATEGORIESItem> cATEGORIES){
		this.cATEGORIES = cATEGORIES;
	}

	public List<CATEGORIESItem> getCATEGORIES(){
		return cATEGORIES;
	}

	@Override
 	public String toString(){
		return 
			"DATA{" + 
			"sEC = '" + sEC + '\'' + 
			",qUOTE = '" + qUOTE + '\'' + 
			",hIGHT_LIGHT = '" + hIGHTLIGHT + '\'' + 
			",pRODUCTS_MENU = '" + pRODUCTSMENU + '\'' + 
			",cATEGORIES = '" + cATEGORIES + '\'' + 
			"}";
		}
}
package com.vugido.com.vugidoeats.models.HomePage;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DATA{

	@JsonProperty("TABLE_NUMBER")
	private String tABLENUMBER;

	@JsonProperty("BRAND_IMAGE")
	private String bRANDIMAGE;

	@JsonProperty("PRODUCTS_MENU")
	private List<PRODUCTSMENUItem> pRODUCTSMENU;

	@JsonProperty("CATEGORIES")
	private List<CATEGORIESItem> cATEGORIES;

	@JsonProperty("CID")
	private String cID;

	public void setTABLENUMBER(String tABLENUMBER){
		this.tABLENUMBER = tABLENUMBER;
	}

	public String getTABLENUMBER(){
		return tABLENUMBER;
	}

	public void setBRANDIMAGE(String bRANDIMAGE){
		this.bRANDIMAGE = bRANDIMAGE;
	}

	public String getBRANDIMAGE(){
		return bRANDIMAGE;
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

	public void setCID(String cID){
		this.cID = cID;
	}

	public String getCID(){
		return cID;
	}

	@Override
 	public String toString(){
		return 
			"DATA{" + 
			"tABLE_NUMBER = '" + tABLENUMBER + '\'' + 
			",bRAND_IMAGE = '" + bRANDIMAGE + '\'' + 
			",pRODUCTS_MENU = '" + pRODUCTSMENU + '\'' + 
			",cATEGORIES = '" + cATEGORIES + '\'' + 
			",cID = '" + cID + '\'' + 
			"}";
		}
}
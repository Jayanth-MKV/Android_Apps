package com.vugido.vos.models.Analytics.Products;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("PRODUCTS")
	private List<PRODUCTSItem> pRODUCTS;

	@JsonProperty("OID")
	private int oID;

	public void setPRODUCTS(List<PRODUCTSItem> pRODUCTS){
		this.pRODUCTS = pRODUCTS;
	}

	public List<PRODUCTSItem> getPRODUCTS(){
		return pRODUCTS;
	}

	public void setOID(int oID){
		this.oID = oID;
	}

	public int getOID(){
		return oID;
	}

	@Override
 	public String toString(){
		return 
			"DATAItem{" + 
			"pRODUCTS = '" + pRODUCTS + '\'' + 
			",oID = '" + oID + '\'' + 
			"}";
		}
}
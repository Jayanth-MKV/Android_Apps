package com.vugido.online_groceries.models.product.categories;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DATA{

	@JsonProperty("PRODUCTS")
	private List<PRODUCTSItem> pRODUCTS;

	@JsonProperty("BRANDS")
	private List<BRANDSItem> bRANDS;

	public void setPRODUCTS(List<PRODUCTSItem> pRODUCTS){
		this.pRODUCTS = pRODUCTS;
	}

	public List<PRODUCTSItem> getPRODUCTS(){
		return pRODUCTS;
	}

	public void setBRANDS(List<BRANDSItem> bRANDS){
		this.bRANDS = bRANDS;
	}

	public List<BRANDSItem> getBRANDS(){
		return bRANDS;
	}

	@Override
 	public String toString(){
		return 
			"DATA{" + 
			"pRODUCTS = '" + pRODUCTS + '\'' + 
			",bRANDS = '" + bRANDS + '\'' + 
			"}";
		}
}
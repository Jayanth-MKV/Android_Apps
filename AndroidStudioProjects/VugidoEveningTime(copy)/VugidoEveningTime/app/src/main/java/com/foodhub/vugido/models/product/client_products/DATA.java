package com.foodhub.vugido.models.product.client_products;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DATA{

	@JsonProperty("FOODS")
	private List<FOODSItem> fOODS;

	@JsonProperty("CATEGORIES")
	private List<CATEGORIESItem> cATEGORIES;

	public void setFOODS(List<FOODSItem> fOODS){
		this.fOODS = fOODS;
	}

	public List<FOODSItem> getFOODS(){
		return fOODS;
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
			"fOODS = '" + fOODS + '\'' + 
			",cATEGORIES = '" + cATEGORIES + '\'' + 
			"}";
		}
}
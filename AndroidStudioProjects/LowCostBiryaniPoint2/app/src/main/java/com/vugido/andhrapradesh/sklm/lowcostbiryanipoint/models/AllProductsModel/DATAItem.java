package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.AllProductsModel;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class DATAItem{

	@JsonProperty("AllProducts")
	private List<AllProductsItem> allProducts;

	public void setAllProducts(List<AllProductsItem> allProducts){
		this.allProducts = allProducts;
	}

	public List<AllProductsItem> getAllProducts(){
		return allProducts;
	}

	@Override
 	public String toString(){
		return 
			"DATAItem{" + 
			"allProducts = '" + allProducts + '\'' + 
			"}";
		}
}
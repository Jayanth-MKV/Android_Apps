package com.dailyneeds.vugido.models.CategoriesModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class CategoriesResponse{

	@JsonProperty("results")
	private List<CategoriesItem> results;

	public void setResults(List<CategoriesItem> results){
		this.results = results;
	}

	public List<CategoriesItem> getResults(){
		return results;
	}

	@Override
 	public String toString(){
		return 
			"CategoriesResponse{" + 
			"results = '" + results + '\'' + 
			"}";
		}
}
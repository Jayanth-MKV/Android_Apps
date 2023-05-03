package com.dailyneeds.vugido.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ComingRes{

	@JsonProperty("error")
	private Error error;

	@JsonProperty("results")
	private List<ResultsItem> results;

	public void setError(Error error){
		this.error = error;
	}

	public Error getError(){
		return error;
	}

	public void setResults(List<ResultsItem> results){
		this.results = results;
	}

	public List<ResultsItem> getResults(){
		return results;
	}

	@Override
 	public String toString(){
		return 
			"ComingRes{" + 
			"error = '" + error + '\'' + 
			",results = '" + results + '\'' + 
			"}";
		}
}
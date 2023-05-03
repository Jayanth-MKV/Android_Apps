package com.dailyneeds.vugido.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ComingSoon{

	@JsonProperty("error")
	private boolean error;

	@JsonProperty("results")
	private List<ResultsItem> results;

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
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
			"ComingSoon{" + 
			"error = '" + error + '\'' + 
			",results = '" + results + '\'' + 
			"}";
		}
}
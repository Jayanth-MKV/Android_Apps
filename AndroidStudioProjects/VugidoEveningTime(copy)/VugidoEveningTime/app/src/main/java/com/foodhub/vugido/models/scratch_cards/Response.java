package com.foodhub.vugido.models.scratch_cards;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response{

	@JsonProperty("data")
	private Data data;

	@JsonProperty("error")
	private boolean error;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
		return error;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"data = '" + data + '\'' + 
			",error = '" + error + '\'' + 
			"}";
		}
}
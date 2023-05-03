package com.foodhub.vugido.models.check_o;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response{

	@JsonProperty("TIME")
	private String tIME;

	@JsonProperty("error")
	private boolean error;

	@JsonProperty("DC")
	private String dC;

	public void setTIME(String tIME){
		this.tIME = tIME;
	}

	public String getTIME(){
		return tIME;
	}

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
		return error;
	}

	public void setDC(String dC){
		this.dC = dC;
	}

	public String getDC(){
		return dC;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"tIME = '" + tIME + '\'' + 
			",error = '" + error + '\'' + 
			",dC = '" + dC + '\'' + 
			"}";
		}
}
package com.vugido.vugidoupdate.models.HomePageDemoModel.HomePageSubModels.RectangularMediumModel;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Response{

	@JsonProperty("DATA")
	private List<DATAItem> dATA;

	@JsonProperty("error")
	private boolean error;

	public void setDATA(List<DATAItem> dATA){
		this.dATA = dATA;
	}

	public List<DATAItem> getDATA(){
		return dATA;
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
			"dATA = '" + dATA + '\'' + 
			",error = '" + error + '\'' + 
			"}";
		}
}
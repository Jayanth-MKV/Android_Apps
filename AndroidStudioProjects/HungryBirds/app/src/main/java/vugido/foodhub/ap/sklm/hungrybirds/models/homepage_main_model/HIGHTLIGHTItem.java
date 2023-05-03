package vugido.foodhub.ap.sklm.hungrybirds.models.homepage_main_model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HIGHTLIGHTItem{

	@JsonProperty("IMAGE")
	private String iMAGE;



	@JsonProperty("PID")
	private int pID;

	public void setIMAGE(String iMAGE){
		this.iMAGE = iMAGE;
	}

	public String getIMAGE(){
		return iMAGE;
	}


	public void setPID(int pID){
		this.pID = pID;
	}

	public int getPID(){
		return pID;
	}

	@Override
 	public String toString(){
		return 
			"HIGHTLIGHTItem{" + 
			"iMAGE = '" + iMAGE + '\'' + 
			",pID = '" + pID + '\'' +
			"}";
		}
}
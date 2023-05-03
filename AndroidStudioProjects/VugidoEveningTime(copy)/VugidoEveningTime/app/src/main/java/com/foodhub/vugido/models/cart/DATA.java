package com.foodhub.vugido.models.cart;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DATA{

	@JsonProperty("PRESENT")
	private List<PRESENTItem> pRESENT;

	@JsonProperty("ABSENT")
	private List<ABSENTItem> aBSENT;

	public void setPRESENT(List<PRESENTItem> pRESENT){
		this.pRESENT = pRESENT;
	}

	public List<PRESENTItem> getPRESENT(){
		return pRESENT;
	}

	public void setABSENT(List<ABSENTItem> aBSENT){
		this.aBSENT = aBSENT;
	}

	public List<ABSENTItem> getABSENT(){
		return aBSENT;
	}

	@Override
 	public String toString(){
		return 
			"DATA{" + 
			"pRESENT = '" + pRESENT + '\'' + 
			",aBSENT = '" + aBSENT + '\'' + 
			"}";
		}
}
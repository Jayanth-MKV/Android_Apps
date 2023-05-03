package com.vugido.online_groceries.models.orders.all_orders;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Response{

	@JsonProperty("ORDERS")
	private List<ORDERSItem> oRDERS;

	@JsonProperty("error")
	private boolean error;

	public void setORDERS(List<ORDERSItem> oRDERS){
		this.oRDERS = oRDERS;
	}

	public List<ORDERSItem> getORDERS(){
		return oRDERS;
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
			"oRDERS = '" + oRDERS + '\'' + 
			",error = '" + error + '\'' + 
			"}";
		}
}
package vugido.foodhub.ap.sklm.hungrybirds.models.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response{

	@JsonProperty("STATUS")
	private boolean sTATUS;

	@JsonProperty("USER")
	private USER uSER;

	public void setSTATUS(boolean sTATUS){
		this.sTATUS = sTATUS;
	}

	public boolean isSTATUS(){
		return sTATUS;
	}

	public void setUSER(USER uSER){
		this.uSER = uSER;
	}

	public USER getUSER(){
		return uSER;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"sTATUS = '" + sTATUS + '\'' + 
			",uSER = '" + uSER + '\'' + 
			"}";
		}
}
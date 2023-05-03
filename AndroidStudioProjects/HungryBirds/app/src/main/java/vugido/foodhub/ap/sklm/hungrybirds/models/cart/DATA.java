package vugido.foodhub.ap.sklm.hungrybirds.models.cart;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DATA{

	@JsonProperty("PRODUCTS")
	private List<PRODUCTSItem> pRODUCTS;

	@JsonProperty("CATEGORIES")
	private List<CATEGORIESItem> cATEGORIES;

	public void setPRODUCTS(List<PRODUCTSItem> pRODUCTS){
		this.pRODUCTS = pRODUCTS;
	}

	public List<PRODUCTSItem> getPRODUCTS(){
		return pRODUCTS;
	}

	public void setCATEGORIES(List<CATEGORIESItem> cATEGORIES){
		this.cATEGORIES = cATEGORIES;
	}

	public List<CATEGORIESItem> getCATEGORIES(){
		return cATEGORIES;
	}

	@Override
 	public String toString(){
		return 
			"DATA{" + 
			"pRODUCTS = '" + pRODUCTS + '\'' + 
			",cATEGORIES = '" + cATEGORIES + '\'' + 
			"}";
		}
}
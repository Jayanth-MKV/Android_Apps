package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.ServiceAvailability;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Response{

	@JsonProperty("LOCATIONS")
	private List<LOCATIONSItem> lOCATIONS;

	public void setLOCATIONS(List<LOCATIONSItem> lOCATIONS){
		this.lOCATIONS = lOCATIONS;
	}

	public List<LOCATIONSItem> getLOCATIONS(){
		return lOCATIONS;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"lOCATIONS = '" + lOCATIONS + '\'' + 
			"}";
		}
}
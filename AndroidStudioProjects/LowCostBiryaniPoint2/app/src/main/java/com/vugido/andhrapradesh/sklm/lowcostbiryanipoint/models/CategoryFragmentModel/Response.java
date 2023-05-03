package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.CategoryFragmentModel;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Response{

	@JsonProperty("DATA")
	private List<DATAItem> dATA;

	public void setDATA(List<DATAItem> dATA){
		this.dATA = dATA;
	}

	public List<DATAItem> getDATA(){
		return dATA;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"dATA = '" + dATA + '\'' + 
			"}";
		}
}
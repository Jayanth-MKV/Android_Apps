package com.vugido.online_groceries.models.homepage.updated;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DATA{

	@JsonProperty("CDES")
	private String cDES;

	@JsonProperty("SLIDER")
	private List<SLIDERItem> sLIDER;

	@JsonProperty("IMAGE")
	private String iMAGE;

	@JsonProperty("CTITLE")
	private String cTITLE;

	@JsonProperty("BTITLE")
	private String bTITLE;

	@JsonProperty("HDES")
	private String hDES;

	@JsonProperty("HOME_PRODUCTS")
	private List<HOMEPRODUCTSItem> hOMEPRODUCTS;

	@JsonProperty("CATEGORIES")
	private List<CATEGORIESItem> cATEGORIES;

	@JsonProperty("BRANDS")
	private List<BRANDSItem> bRANDS;

	@JsonProperty("HTITLE")
	private String hTITLE;

	public void setCDES(String cDES){
		this.cDES = cDES;
	}

	public String getCDES(){
		return cDES;
	}

	public void setSLIDER(List<SLIDERItem> sLIDER){
		this.sLIDER = sLIDER;
	}

	public List<SLIDERItem> getSLIDER(){
		return sLIDER;
	}

	public void setIMAGE(String iMAGE){
		this.iMAGE = iMAGE;
	}

	public String getIMAGE(){
		return iMAGE;
	}

	public void setCTITLE(String cTITLE){
		this.cTITLE = cTITLE;
	}

	public String getCTITLE(){
		return cTITLE;
	}

	public void setBTITLE(String bTITLE){
		this.bTITLE = bTITLE;
	}

	public String getBTITLE(){
		return bTITLE;
	}

	public void setHDES(String hDES){
		this.hDES = hDES;
	}

	public String getHDES(){
		return hDES;
	}

	public void setHOMEPRODUCTS(List<HOMEPRODUCTSItem> hOMEPRODUCTS){
		this.hOMEPRODUCTS = hOMEPRODUCTS;
	}

	public List<HOMEPRODUCTSItem> getHOMEPRODUCTS(){
		return hOMEPRODUCTS;
	}

	public void setCATEGORIES(List<CATEGORIESItem> cATEGORIES){
		this.cATEGORIES = cATEGORIES;
	}

	public List<CATEGORIESItem> getCATEGORIES(){
		return cATEGORIES;
	}

	public void setBRANDS(List<BRANDSItem> bRANDS){
		this.bRANDS = bRANDS;
	}

	public List<BRANDSItem> getBRANDS(){
		return bRANDS;
	}

	public void setHTITLE(String hTITLE){
		this.hTITLE = hTITLE;
	}

	public String getHTITLE(){
		return hTITLE;
	}

	@Override
 	public String toString(){
		return 
			"DATA{" + 
			"cDES = '" + cDES + '\'' + 
			",sLIDER = '" + sLIDER + '\'' + 
			",iMAGE = '" + iMAGE + '\'' + 
			",cTITLE = '" + cTITLE + '\'' + 
			",bTITLE = '" + bTITLE + '\'' + 
			",hDES = '" + hDES + '\'' + 
			",hOME_PRODUCTS = '" + hOMEPRODUCTS + '\'' + 
			",cATEGORIES = '" + cATEGORIES + '\'' + 
			",bRANDS = '" + bRANDS + '\'' + 
			",hTITLE = '" + hTITLE + '\'' + 
			"}";
		}
}
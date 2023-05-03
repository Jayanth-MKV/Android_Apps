package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.Transactions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATAItem{

	@JsonProperty("COINS")
	private int cOINS;

	@JsonProperty("COINS_STATE")
	private int cOINSSTATE;

	@JsonProperty("ID")
	private int iD;

	@JsonProperty("REASON")
	private String rEASON;

	@JsonProperty("TRANSACTION_TYPE")
	private int tRANSACTIONTYPE;

	@JsonProperty("ON")
	private String oN;

	public void setCOINS(int cOINS){
		this.cOINS = cOINS;
	}

	public int getCOINS(){
		return cOINS;
	}

	public void setCOINSSTATE(int cOINSSTATE){
		this.cOINSSTATE = cOINSSTATE;
	}

	public int getCOINSSTATE(){
		return cOINSSTATE;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setREASON(String rEASON){
		this.rEASON = rEASON;
	}

	public String getREASON(){
		return rEASON;
	}

	public void setTRANSACTIONTYPE(int tRANSACTIONTYPE){
		this.tRANSACTIONTYPE = tRANSACTIONTYPE;
	}

	public int getTRANSACTIONTYPE(){
		return tRANSACTIONTYPE;
	}

	public void setON(String oN){
		this.oN = oN;
	}

	public String getON(){
		return oN;
	}

	@Override
 	public String toString(){
		return 
			"DATAItem{" + 
			"cOINS = '" + cOINS + '\'' + 
			",cOINS_STATE = '" + cOINSSTATE + '\'' + 
			",iD = '" + iD + '\'' + 
			",rEASON = '" + rEASON + '\'' + 
			",tRANSACTION_TYPE = '" + tRANSACTIONTYPE + '\'' + 
			",oN = '" + oN + '\'' + 
			"}";
		}
}
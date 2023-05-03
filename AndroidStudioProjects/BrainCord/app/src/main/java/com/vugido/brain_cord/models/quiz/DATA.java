package com.vugido.brain_cord.models.quiz;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATA{

	@JsonProperty("Q_MCQS")
	private QMCQS qMCQS;

	@JsonProperty("Q_TYPE")
	private int qTYPE;

	@JsonProperty("Q_IMG")
	private QIMG qIMG;

	@JsonProperty("Q_PARA")
	private QPARA qPARA;

	@JsonProperty("Q_MCQM")
	private QMCQM qMCQM;

	@JsonProperty("QID")
	private int qID;

	@JsonProperty("SID")
	private int sID;

	public void setQMCQS(QMCQS qMCQS){
		this.qMCQS = qMCQS;
	}

	public QMCQS getQMCQS(){
		return qMCQS;
	}

	public void setQTYPE(int qTYPE){
		this.qTYPE = qTYPE;
	}

	public int getQTYPE(){
		return qTYPE;
	}

	public void setQIMG(QIMG qIMG){
		this.qIMG = qIMG;
	}

	public QIMG getQIMG(){
		return qIMG;
	}

	public void setQPARA(QPARA qPARA){
		this.qPARA = qPARA;
	}

	public QPARA getQPARA(){
		return qPARA;
	}

	public void setQMCQM(QMCQM qMCQM){
		this.qMCQM = qMCQM;
	}

	public QMCQM getQMCQM(){
		return qMCQM;
	}

	public void setQID(int qID){
		this.qID = qID;
	}

	public int getQID(){
		return qID;
	}

	public void setSID(int sID){
		this.sID = sID;
	}

	public int getSID(){
		return sID;
	}

	@Override
 	public String toString(){
		return 
			"DATA{" + 
			"q_MCQS = '" + qMCQS + '\'' + 
			",q_TYPE = '" + qTYPE + '\'' + 
			",q_IMG = '" + qIMG + '\'' + 
			",q_PARA = '" + qPARA + '\'' + 
			",q_MCQM = '" + qMCQM + '\'' + 
			",qID = '" + qID + '\'' + 
			",sID = '" + sID + '\'' + 
			"}";
		}
}
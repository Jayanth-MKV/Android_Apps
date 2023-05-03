package com.vugido.brain_cord.models.section;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DATA{

	@JsonProperty("SEC")
	private String sEC;

	@JsonProperty("question_limit")
	private int questionLimit;

	@JsonProperty("time_limit")
	private int timeLimit;

	@JsonProperty("ID")
	private int iD;

	public void setSEC(String sEC){
		this.sEC = sEC;
	}

	public String getSEC(){
		return sEC;
	}

	public void setQuestionLimit(int questionLimit){
		this.questionLimit = questionLimit;
	}

	public int getQuestionLimit(){
		return questionLimit;
	}

	public void setTimeLimit(int timeLimit){
		this.timeLimit = timeLimit;
	}

	public int getTimeLimit(){
		return timeLimit;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	@Override
 	public String toString(){
		return 
			"DATA{" + 
			"sEC = '" + sEC + '\'' + 
			",question_limit = '" + questionLimit + '\'' + 
			",time_limit = '" + timeLimit + '\'' + 
			",iD = '" + iD + '\'' + 
			"}";
		}
}
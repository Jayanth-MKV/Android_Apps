package com.vugido.brain_cord.models.quiz;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QPARA{

	@JsonProperty("paragraph")
	private String paragraph;

	@JsonProperty("question_two_option_one")
	private String questionTwoOptionOne;

	@JsonProperty("question_three_option_two")
	private String questionThreeOptionTwo;

	@JsonProperty("question_one_option_one")
	private String questionOneOptionOne;

	@JsonProperty("question_two_option_two")
	private String questionTwoOptionTwo;

	@JsonProperty("question_two_option_three")
	private String questionTwoOptionThree;

	@JsonProperty("question_three_option_one")
	private String questionThreeOptionOne;

	@JsonProperty("question_one_option_four")
	private String questionOneOptionFour;

	@JsonProperty("question_one_option_three")
	private String questionOneOptionThree;

	@JsonProperty("question_two_option_four")
	private String questionTwoOptionFour;

	@JsonProperty("question_one")
	private String questionOne;

	@JsonProperty("question_two")
	private String questionTwo;

	@JsonProperty("question_three")
	private String questionThree;

	@JsonProperty("question_three_option_three")
	private String questionThreeOptionThree;

	@JsonProperty("ID")
	private int iD;

	@JsonProperty("question_three_option_four")
	private String questionThreeOptionFour;

	@JsonProperty("question_one_option_two")
	private String questionOneOptionTwo;

	public void setParagraph(String paragraph){
		this.paragraph = paragraph;
	}

	public String getParagraph(){
		return paragraph;
	}

	public void setQuestionTwoOptionOne(String questionTwoOptionOne){
		this.questionTwoOptionOne = questionTwoOptionOne;
	}

	public String getQuestionTwoOptionOne(){
		return questionTwoOptionOne;
	}

	public void setQuestionThreeOptionTwo(String questionThreeOptionTwo){
		this.questionThreeOptionTwo = questionThreeOptionTwo;
	}

	public String getQuestionThreeOptionTwo(){
		return questionThreeOptionTwo;
	}

	public void setQuestionOneOptionOne(String questionOneOptionOne){
		this.questionOneOptionOne = questionOneOptionOne;
	}

	public String getQuestionOneOptionOne(){
		return questionOneOptionOne;
	}

	public void setQuestionTwoOptionTwo(String questionTwoOptionTwo){
		this.questionTwoOptionTwo = questionTwoOptionTwo;
	}

	public String getQuestionTwoOptionTwo(){
		return questionTwoOptionTwo;
	}

	public void setQuestionTwoOptionThree(String questionTwoOptionThree){
		this.questionTwoOptionThree = questionTwoOptionThree;
	}

	public String getQuestionTwoOptionThree(){
		return questionTwoOptionThree;
	}

	public void setQuestionThreeOptionOne(String questionThreeOptionOne){
		this.questionThreeOptionOne = questionThreeOptionOne;
	}

	public String getQuestionThreeOptionOne(){
		return questionThreeOptionOne;
	}

	public void setQuestionOneOptionFour(String questionOneOptionFour){
		this.questionOneOptionFour = questionOneOptionFour;
	}

	public String getQuestionOneOptionFour(){
		return questionOneOptionFour;
	}

	public void setQuestionOneOptionThree(String questionOneOptionThree){
		this.questionOneOptionThree = questionOneOptionThree;
	}

	public String getQuestionOneOptionThree(){
		return questionOneOptionThree;
	}

	public void setQuestionTwoOptionFour(String questionTwoOptionFour){
		this.questionTwoOptionFour = questionTwoOptionFour;
	}

	public String getQuestionTwoOptionFour(){
		return questionTwoOptionFour;
	}

	public void setQuestionOne(String questionOne){
		this.questionOne = questionOne;
	}

	public String getQuestionOne(){
		return questionOne;
	}

	public void setQuestionTwo(String questionTwo){
		this.questionTwo = questionTwo;
	}

	public String getQuestionTwo(){
		return questionTwo;
	}

	public void setQuestionThree(String questionThree){
		this.questionThree = questionThree;
	}

	public String getQuestionThree(){
		return questionThree;
	}

	public void setQuestionThreeOptionThree(String questionThreeOptionThree){
		this.questionThreeOptionThree = questionThreeOptionThree;
	}

	public String getQuestionThreeOptionThree(){
		return questionThreeOptionThree;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setQuestionThreeOptionFour(String questionThreeOptionFour){
		this.questionThreeOptionFour = questionThreeOptionFour;
	}

	public String getQuestionThreeOptionFour(){
		return questionThreeOptionFour;
	}

	public void setQuestionOneOptionTwo(String questionOneOptionTwo){
		this.questionOneOptionTwo = questionOneOptionTwo;
	}

	public String getQuestionOneOptionTwo(){
		return questionOneOptionTwo;
	}

	@Override
 	public String toString(){
		return 
			"QPARA{" + 
			"paragraph = '" + paragraph + '\'' + 
			",question_two_option_one = '" + questionTwoOptionOne + '\'' + 
			",question_three_option_two = '" + questionThreeOptionTwo + '\'' + 
			",question_one_option_one = '" + questionOneOptionOne + '\'' + 
			",question_two_option_two = '" + questionTwoOptionTwo + '\'' + 
			",question_two_option_three = '" + questionTwoOptionThree + '\'' + 
			",question_three_option_one = '" + questionThreeOptionOne + '\'' + 
			",question_one_option_four = '" + questionOneOptionFour + '\'' + 
			",question_one_option_three = '" + questionOneOptionThree + '\'' + 
			",question_two_option_four = '" + questionTwoOptionFour + '\'' + 
			",question_one = '" + questionOne + '\'' + 
			",question_two = '" + questionTwo + '\'' + 
			",question_three = '" + questionThree + '\'' + 
			",question_three_option_three = '" + questionThreeOptionThree + '\'' + 
			",iD = '" + iD + '\'' + 
			",question_three_option_four = '" + questionThreeOptionFour + '\'' + 
			",question_one_option_two = '" + questionOneOptionTwo + '\'' + 
			"}";
		}
}
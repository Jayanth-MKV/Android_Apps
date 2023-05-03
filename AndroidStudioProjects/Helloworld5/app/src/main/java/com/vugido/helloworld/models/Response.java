package com.vugido.helloworld.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Response{

	@JsonProperty("data")
	private List<DataItem> data;

	@JsonProperty("login")
	private boolean login;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setLogin(boolean login){
		this.login = login;
	}

	public boolean isLogin(){
		return login;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"data = '" + data + '\'' + 
			",login = '" + login + '\'' + 
			"}";
		}
}
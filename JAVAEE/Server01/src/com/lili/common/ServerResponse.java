package com.lili.common;

import com.google.gson.Gson;

public class ServerResponse<T> {
	private int status;
	private String msg;
	private T data;
	private ServerResponse() {}
	private ServerResponse(int status) {
		this.status = status;
	}
	private ServerResponse(int status,String msg) {
		this.status = status;
		this.msg = msg;
	}

	private ServerResponse(int status,T data) {
		this.status = status;
		this.data = data;
	}
	private ServerResponse(int status,String msg,T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	/**
	 * 将格式转换成JSON
	 * */
	public String objToJson(){
		Gson gson = new Gson();
		String responseText = gson.toJson(this);
		return responseText;
	}
	/**
	 * 判断是否成功
	 * */
	public boolean isSucess() {
		return this.status == 0;
	}
	/**
	 * 接口连接成功
	 * */
	public static ServerResponse creatResponseBySucess() {
		return new ServerResponse(0);
	}
	public static ServerResponse creatResponseBySucess(String msg){
		return new ServerResponse(0,msg);
	}
	public static <T> ServerResponse creatResponseBySucess(String msg,T data){
		return new ServerResponse<T>(0,msg, data);
	}
	/**
	 * 接口连接失败
	 * */
	public static ServerResponse creatResponseByFail(int status) {
		return new ServerResponse (status);
	}
	public static ServerResponse creatResponseByFail(int status,String msg) {
		return new ServerResponse (status, msg);
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}	
}

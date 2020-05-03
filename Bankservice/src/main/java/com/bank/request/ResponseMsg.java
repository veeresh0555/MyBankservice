package com.bank.request;

public class ResponseMsg {
	
	private String errcode;
	private String message;
	 
	  public ResponseMsg(String errcode,String msg){
	    this.message = msg;
	    this.errcode=errcode;
	  }
	  
	  public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getMessage() {
	    return message;
	  }
	 
	  public void setMessage(String message) {
	    this.message = message;
	  }
	
	
	
}

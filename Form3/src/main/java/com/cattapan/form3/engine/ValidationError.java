package com.cattapan.form3.engine;

public class ValidationError {

    private String arg;
    private String message;

    public String getArg() {
	return arg;
    }

    public void setArg(String arg) {
	this.arg = arg;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public ValidationError(String arg, String message) {
	super();
	this.arg = arg;
	this.message = message;
    }

    public ValidationError() {

    }

}

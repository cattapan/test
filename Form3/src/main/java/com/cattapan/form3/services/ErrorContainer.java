package com.cattapan.form3.services;

import java.util.Collection;

import com.cattapan.form3.engine.ValidationError;

public class ErrorContainer {
    private Collection<ValidationError> errors;

    public ErrorContainer(Collection<ValidationError> errors) {
	this.errors = errors;
    }

    public Collection<ValidationError> getErrors() {
	return errors;
    }

    public void setErrors(Collection<ValidationError> errors) {
	this.errors = errors;
    }
}

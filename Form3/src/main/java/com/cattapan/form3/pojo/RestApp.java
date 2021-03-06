package com.cattapan.form3.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.cattapan.form3.services.PaymentServiceImpl;

public class RestApp extends Application {
    private Set<Object> singletons = new HashSet<Object>();

    public RestApp() {
	singletons.add(new PaymentServiceImpl());
    }

    @Override
    public Set<Object> getSingletons() {
	return singletons;
    }
}

package com.cattapan.form3.services;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class ResteasyBootstrap extends Application {
    private Set<Object> singletons = new HashSet<Object>();

    public ResteasyBootstrap() {
	singletons.add(new PaymentServiceImpl());
    }

    @Override
    public Set<Object> getSingletons() {
	return singletons;
    }
}

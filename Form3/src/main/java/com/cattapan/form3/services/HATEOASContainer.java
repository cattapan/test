package com.cattapan.form3.services;

import java.util.HashMap;
import java.util.Map;

public class HATEOASContainer {

    private Object data = null;
    private Map<String, String> links = new HashMap<String, String>();
    

    void setSelf(String value) {
	this.addLink("self", value);
    }

    private void addLink(String k, String v) {
	this.links.put(k, v);
    }

    public Object getData() {
	return data;
    }

    void setData(Object data) {
	this.data = data;
    }

    public Map<String, String> getLinks() {
	return links;
    }

}

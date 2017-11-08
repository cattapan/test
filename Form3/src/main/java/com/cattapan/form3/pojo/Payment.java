package com.cattapan.form3.pojo;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Payment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;
    private String type = null;
    private String version;

    @JsonProperty("organisation_id")
    private String organisationId;
    @Embedded
    private PaymentAttributes attributes;

    public void setType(String type) {
	this.type = type;
    }

    public String getType() {
	return type;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getVersion() {
	return version;
    }

    public void setVersion(String version) {
	this.version = version;
    }

    public String getOrganisationId() {
	return organisationId;
    }

    public void setOrganisationId(String organisationId) {
	this.organisationId = organisationId;
    }

    public PaymentAttributes getAttributes() {
	return attributes;
    }

    public void setAttributes(PaymentAttributes attributes) {
	this.attributes = attributes;
    }

}

package com.cattapan.form3.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Charges {

    @Id
    @GeneratedValue(generator="UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "charge_amount")
    private String amount;
    // TODO change to type currencyCode;

    @Column(name = "charge_currency")
    private String currency;

    public String getAmount() {
	return amount;
    }

    public void setAmount(String amount) {
	this.amount = amount;
    }

    public String getCurrency() {
	return currency;
    }

    public void setCurrency(String currency) {
	this.currency = currency;
    }

}

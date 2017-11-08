package com.cattapan.form3.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Party {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @JsonProperty("account_name")
    private String accountName;

    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("account_number_code")
    private String accountNumberCode;

    // TODO if account type is part of ID, need to implement a sub type for
    // beneficiaryParty then
    @JsonProperty("account_type")
    private String accountType;

    @JsonProperty("bank_id")
    private String bankID;

    @JsonProperty("bank_id_code")
    private String bankIDCode;
    private String address;
    private String name;

    public String getAccountName() {
	return accountName;
    }

    public void setAccountName(String accountName) {
	this.accountName = accountName;
    }

    public String getAccountNumber() {
	return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
    }

    public String getAccountNumberCode() {
	return accountNumberCode;
    }

    public void setAccountNumberCode(String accountNumberCode) {
	this.accountNumberCode = accountNumberCode;
    }

    public String getAccountType() {
	return accountType;
    }

    public void setAccountType(String accountType) {
	this.accountType = accountType;
    }

    public String getBankID() {
	return bankID;
    }

    public void setBankID(String bankID) {
	this.bankID = bankID;
    }

    public String getBankIDCode() {
	return bankIDCode;
    }

    public void setBankIDCode(String bankIDCode) {
	this.bankIDCode = bankIDCode;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

}

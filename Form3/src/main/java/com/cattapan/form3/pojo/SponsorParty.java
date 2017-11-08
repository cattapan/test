package com.cattapan.form3.pojo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class SponsorParty {

    @JsonProperty("account_number")
    @Column(name = "spAccountNumber")
    private String accountNumber;

    @JsonProperty("bank_id")
    @Column(name = "spBankID")
    private String bankID;

    @JsonProperty("bank_id_code")
    @Column(name = "spBankIDCode")
    private String bankIDCode;

    public String getAccountNumber() {
	return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
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

}

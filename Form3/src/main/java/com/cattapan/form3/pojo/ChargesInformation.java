package com.cattapan.form3.pojo;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class ChargesInformation {

    @JsonProperty("bearer_code")
    private String bearerCode;

    @JsonProperty("sender_charges")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id")
    private Collection<Charges> senderCharges;

    @JsonProperty("receiver_charges_amount")
    private String receiverChargesAmount;

    @JsonProperty("receiver_charges_currency")
    private String receiverChargesCurrency;

    public String getBearerCode() {
	return bearerCode;
    }

    public void setBearerCode(String bearerCode) {
	this.bearerCode = bearerCode;
    }

    public Collection<Charges> getSenderCharges() {
	return senderCharges;
    }

    public void setSenderCharges(Collection<Charges> senderCharges) {
	this.senderCharges = senderCharges;
    }

    public String getReceiverChargesAmount() {
	return receiverChargesAmount;
    }

    public void setReceiverChargesAmount(String receiverChargesAmount) {
	this.receiverChargesAmount = receiverChargesAmount;
    }

    public String getReceiverChargesCurrency() {
	return receiverChargesCurrency;
    }

    public void setReceiverChargesCurrency(String receiverChargesCurrency) {
	this.receiverChargesCurrency = receiverChargesCurrency;
    }

}

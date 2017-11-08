package com.cattapan.form3.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class PaymentAttributes {

    @Column(name = "paymentAmount")
    private String amount;

    @Embedded
    @JsonProperty("charges_information")
    private ChargesInformation chargesInformation;

    private String currency;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonProperty("debtor_party")
    private Party debtorParty;

    @JsonProperty("beneficiary_party")
    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    private Party beneficiaryParty;

    @JsonProperty("end_to_end_reference")
    private String endToEndReference;
    @Embedded
    private FXData fx;
    @JsonProperty("numeric_reference")
    private String numericReference;
    @JsonProperty("payment_id")
    private String paymentId;
    @JsonProperty("payment_purpose")
    private String paymentPurpose;
    @JsonProperty("payment_scheme")
    private String paymentScheme;
    @JsonProperty("payment_type")
    private String paymentType;
    @JsonProperty("processing_date")
    private String processingDate;
    private String reference;
    @JsonProperty("scheme_payment_sub_type")
    private String schemePaymentSubType;
    @JsonProperty("scheme_payment_type")
    private String schemePaymentType;
    @Embedded
    @JsonProperty("sponsor_party")
    private SponsorParty sponsorParty;

    public String getAmount() {
	return amount;
    }

    public void setAmount(String amount) {
	this.amount = amount;
    }

    public ChargesInformation getChargesInformation() {
	return chargesInformation;
    }

    public void setChargesInformation(ChargesInformation chargesInformation) {
	this.chargesInformation = chargesInformation;
    }

    public String getCurrency() {
	return currency;
    }

    public void setCurrency(String currency) {
	this.currency = currency;
    }

    public Party getDebtorParty() {
	return debtorParty;
    }

    public void setDebtorParty(Party debtorParty) {
	this.debtorParty = debtorParty;
    }

    public Party getBeneficiaryParty() {
	return beneficiaryParty;
    }

    public void setBeneficiaryParty(Party beneficiaryParty) {
	this.beneficiaryParty = beneficiaryParty;
    }

    public String getEndToEndReference() {
	return endToEndReference;
    }

    public void setEndToEndReference(String endToEndReference) {
	this.endToEndReference = endToEndReference;
    }

    public FXData getFx() {
	return fx;
    }

    public void setFx(FXData fx) {
	this.fx = fx;
    }

    public String getNumericReference() {
	return numericReference;
    }

    public void setNumericReference(String numericReference) {
	this.numericReference = numericReference;
    }

    public String getPaymentId() {
	return paymentId;
    }

    public void setPaymentId(String paymentId) {
	this.paymentId = paymentId;
    }

    public String getPaymentPurpose() {
	return paymentPurpose;
    }

    public void setPaymentPurpose(String paymentPurpose) {
	this.paymentPurpose = paymentPurpose;
    }

    public String getPaymentScheme() {
	return paymentScheme;
    }

    public void setPaymentScheme(String paymentScheme) {
	this.paymentScheme = paymentScheme;
    }

    public String getPaymentType() {
	return paymentType;
    }

    public void setPaymentType(String paymentType) {
	this.paymentType = paymentType;
    }

    public String getProcessingDate() {
	return processingDate;
    }

    public void setProcessingDate(String processingDate) {
	this.processingDate = processingDate;
    }

    public String getReference() {
	return reference;
    }

    public void setReference(String reference) {
	this.reference = reference;
    }

    public String getSchemePaymentSubType() {
	return schemePaymentSubType;
    }

    public void setSchemePaymentSubType(String schemePaymentSubType) {
	this.schemePaymentSubType = schemePaymentSubType;
    }

    public String getSchemePaymentType() {
	return schemePaymentType;
    }

    public void setSchemePaymentType(String schemePaymentType) {
	this.schemePaymentType = schemePaymentType;
    }

    public SponsorParty getSponsorParty() {
	return sponsorParty;
    }

    public void setSponsorParty(SponsorParty sponsorParty) {
	this.sponsorParty = sponsorParty;
    }

}

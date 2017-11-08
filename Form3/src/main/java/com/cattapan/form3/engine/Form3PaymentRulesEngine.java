package com.cattapan.form3.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cattapan.form3.pojo.Party;
import com.cattapan.form3.pojo.Payment;
import com.cattapan.form3.pojo.PaymentAttributes;

public class Form3PaymentRulesEngine {

    // private static final Logger logger =
    // LogManager.getLogger(Form3PaymentRulesEngine.class);

    // TODO add the attributes literal using field names, not strings
    private static final String FULL_NAME_REGEX = "^[A-Za-z\\s]{2,}[\\.]{0,1}[A-Za-z\\s]{2,}$";
    private static final Double MAX_AMOUNT = 10000d;

    public boolean isNameValid(String name) {
	if (name != null && !name.isEmpty()) {

	    Matcher m = Pattern.compile(FULL_NAME_REGEX).matcher(name);
	    return m.matches();
	}
	return false;
    }

    public Collection<ValidationError> validatePayment(Payment p) {

	Collection<ValidationError> errors = new ArrayList<ValidationError>();

	if (!"Payment".equals(p.getType())) {
	    errors.add(new ValidationError("type", "Only type Payment is accepted"));
	}

	PaymentAttributes attributes = p.getAttributes();
	if (attributes == null) {
	    errors.add(new ValidationError("attributes", "Payment attributes cannot be empty"));
	}

	validatePaymentAmount(errors, attributes.getAmount());

	Party beneficiaryParty = attributes.getBeneficiaryParty();
	Party debtorParty = attributes.getDebtorParty();

	if (beneficiaryParty == null) {
	    errors.add(new ValidationError("attributes.beneficiaryParty", "Beneficiary Party is required"));
	} else {
	    if (!isNameValid(beneficiaryParty.getName())) {
		errors.add(new ValidationError("attributes.beneficiaryParty.name", "Beneficiary Party name is invalid"));
	    }
	}

	if (debtorParty == null) {
	    errors.add(new ValidationError("attributes.debtorParty", "Debtor Party is required"));
	} else {
	    if (!isNameValid(debtorParty.getName())) {
		errors.add(new ValidationError("attributes.debtorParty.name", "Debtor Party name is invalid"));
	    }
	    if (debtorParty.getBankID() == null || debtorParty.getBankID().equals("1234")) {
		errors.add(new ValidationError("attributes.debtorParty.bankID", "Debtor Party bank is not accepted. Please choose another bank"));
	    }
	}

	return errors.isEmpty() ? null : errors;
    }

    private void validatePaymentAmount(Collection<ValidationError> errors, String amount) {

	try {
	    Double d = Double.valueOf(amount);
	    if (d > MAX_AMOUNT) {
		errors.add(new ValidationError("attributes.amount", "Amount must be lower than " + MAX_AMOUNT));
	    }
	} catch (NumberFormatException nfe) {
	    errors.add(new ValidationError("attributes.amount", "Invalid number"));
	}
    }
}

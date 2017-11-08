package com.cattapan.form3.auth;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.http.auth.InvalidCredentialsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Provider
@PreMatching
@Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {

    private static final Logger logger = LogManager.getLogger(AuthFilter.class);
    private static final String API_KEY_HEADER_NAME = "API-Key";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

	final String apiKey = requestContext.getHeaderString(API_KEY_HEADER_NAME);

	try {

	    // Validate the token
	    validateKey(apiKey);
	    logger.trace("Key validated " + apiKey);
	} catch (InvalidCredentialsException e) {
	    logger.warn("Invalid api key access attempt. Key: " + apiKey);
	    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
	}
    }

    /*
     * Full authentication mechanism is beyond the scope of this implementation
     * All api key longer than 10 chars will be valid
     */
    private void validateKey(String apiKey) throws InvalidCredentialsException {
	if (apiKey == null || apiKey.trim().length() < 10) {
	    throw new InvalidCredentialsException("API Key provided is invalid");
	}
    }

}

package com.cattapan.form3.services;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.resteasy.annotations.providers.jackson.Formatted;

import com.cattapan.form3.engine.Form3PaymentRulesEngine;
import com.cattapan.form3.engine.ValidationError;
import com.cattapan.form3.persistence.PaymentDAO;
import com.cattapan.form3.pojo.Payment;

@Path("/payments")
public class PaymentServiceImpl {

    // TODO DOCUMENT

    private static final Logger logger = LogManager.getLogger(PaymentServiceImpl.class);
    private Form3PaymentRulesEngine paymentRules = new Form3PaymentRulesEngine();

    @Context
    private UriInfo uriInfo;

    @PUT
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response updatePayment(Payment p) {
	try {
	    PaymentDAO pDAO = new PaymentDAO();
	    Collection<ValidationError> errors = paymentRules.validatePayment(p);
	    if (errors == null) {

		p = pDAO.update(p);
		if (p != null) {
		    Link link = Link.fromUri(uriInfo.getBaseUriBuilder().path(getClass()).path(getClass(), "getPayment").build(p.getId())).rel("self").type("GET").build();

		    HATEOASContainer container = new HATEOASContainer();
		    container.setSelf(link.getUri().toString());
		    container.setData(p);
		    return Response.ok(container).build();

		} else {
		    return Response.status(Status.NOT_FOUND).build();

		}
	    } else {
		logger.debug("Payment validation found errors. Returning error collection " + errors);

		ErrorContainer container = new ErrorContainer(errors);
		container.setErrors(errors);

		return Response.status(422).entity(container).build();
	    }
	} catch (Exception e) {
	    logger.error("Error updating payment Id " + p.getId(), e);
	    return Response.serverError().build();

	}
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response addPayment(Payment p) {
	try {
	    PaymentDAO pDAO = new PaymentDAO();

	    Collection<ValidationError> errors = paymentRules.validatePayment(p);
	    if (errors == null) {
		p = pDAO.create(p);
		if (p != null) {

		    Link link = Link.fromUri(uriInfo.getBaseUriBuilder().path(getClass()).path(getClass(), "getPayment").build(p.getId())).rel("self").type("GET").build();

		    HATEOASContainer container = new HATEOASContainer();
		    container.setSelf(link.getUri().toString());
		    container.setData(p);
		    return Response.ok(container).build();

		} else {
		    return Response.status(422).build();
		}
	    } else {
		logger.debug("Payment validation found errors. Returning error collection " + errors);

		ErrorContainer container = new ErrorContainer(errors);
		container.setErrors(errors);

		return Response.status(422).entity(container).build();
	    }

	} catch (Exception e) {
	    logger.error("Error adding payment ", e);
	    return Response.serverError().build();
	}
    }

    @DELETE
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("{id}")
    public Response deletePayment(@PathParam("id") String id) {
	try {

	    PaymentDAO dao = new PaymentDAO();
	    Payment p = dao.find(id);
	    if (p == null) {
		logger.info("Trying to remove a resource but it wasn't found. Id: " + id);
		return Response.status(Status.NOT_FOUND).build();
	    }

	    logger.debug("Removing resource of Id: " + id);
	    if (dao.delete(p)) {
		return Response.ok().build();
	    } else {
		logger.debug("Removing resource of Id FAILED. Returning not modified. Id: " + id);
		return Response.notModified().build();
	    }
	} catch (Exception e) {
	    logger.error("Error deleting payment with Id " + id, e);
	    return Response.serverError().build();
	}

    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Formatted
    @Path("{id}")
    public Response getPayment(@PathParam("id") String id) {

	try {
	    PaymentDAO dao = new PaymentDAO();
	    HATEOASContainer container = new HATEOASContainer();
	    Link link = Link.fromUri(uriInfo.getRequestUri()).build();
	    Payment p = dao.find(id);
	    if (p == null) {
		return Response.status(Status.NOT_FOUND).build();
	    }
	    container.setData(p);
	    container.setSelf(link.getUri().toString());
	    return Response.ok(container).build();
	} catch (Exception e) {
	    logger.error("Error finding payment with Id " + id, e);
	    return Response.serverError().build();

	}
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getAllPayments(@QueryParam("orgId") String orgId,
	    @QueryParam("max") int maxResults, @QueryParam("delta") int startPosition) {

	PaymentDAO dao = new PaymentDAO();
	Collection<Payment> result = null;
	if (orgId != null && !orgId.trim().isEmpty()) {
	    result = dao.findByOrganisation(orgId, maxResults, startPosition);
	} else {
	    result = dao.findAll();
	}
	HATEOASContainer container = new HATEOASContainer();
	Link link = Link.fromUri(uriInfo.getRequestUri()).build();

	container.setData(result);
	container.setSelf(link.getUri().toString());
	return Response.ok(container).build();
    }
}

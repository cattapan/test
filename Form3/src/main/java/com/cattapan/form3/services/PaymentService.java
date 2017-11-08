package com.cattapan.form3.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.providers.jackson.Formatted;

import com.cattapan.form3.pojo.Payment;

public interface PaymentService {

    @PUT
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response addPayment(Payment p);

    @DELETE
    @Path("/payment/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response deletePayment(int id);

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Formatted
    public Payment getPayment(String id);

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Payment> getAllPayments();

}

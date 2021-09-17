package com.rest.training;

import java.util.Collections;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Root resource (exposed at "myresource" path)
 * // employees
 * // users
 * // addresses
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @Path("json")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson() {
        Map<String, String> entity = Collections.singletonMap("greeting", "Hi from a REST endpoint");
        return Response.ok(entity).build();
    }
}

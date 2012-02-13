package com.quova.platform.compliance.api.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sessions")
public interface SessionResource {

  @GET
  @Produces({MediaType.APPLICATION_XML})
  @Path("/new")
  public Response createSession();

}

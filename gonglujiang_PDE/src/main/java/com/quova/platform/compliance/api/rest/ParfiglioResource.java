package com.quova.platform.compliance.api.rest;

import com.quova.platform.compliance.services.ParfiglioService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/parfiglio")
public interface ParfiglioResource {

  /*
   * Note that the method is intentionally given a vague name to prevent malicious users
   * from guessing its intent.
   */
  @GET
  @Produces({MediaType.APPLICATION_XML})
  @Path("/generate")
  public Response generateParfiglio();

}

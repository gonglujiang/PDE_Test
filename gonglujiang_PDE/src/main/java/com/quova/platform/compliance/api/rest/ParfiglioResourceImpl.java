package com.quova.platform.compliance.api.rest;

import com.quova.platform.compliance.dto.QParfiglio;
import com.quova.platform.compliance.services.ParfiglioService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Controller
@Path("/parfiglio")
public class ParfiglioResourceImpl extends BaseResource implements ParfiglioResource {

  private static final Logger logger = Logger.getLogger(ParfiglioResourceImpl.class);

  @Autowired
  private ParfiglioService parfiglioService;

  /*
   * Note that the method is intentionally given a vague name to prevent malicious users
   * from guessing its intent.
   */
  @GET
  @Produces({MediaType.APPLICATION_XML})
  @Path("/generate")
  public Response generateParfiglio() {
    Throwable t = null;
    Object o = null;

    try {
      QParfiglio parfiglio = parfiglioService.getParfiglio();
      o = parfiglio;
    } catch( Exception e ) {
      t = e;
    }

    return buildResponse( o, t );

  }

}

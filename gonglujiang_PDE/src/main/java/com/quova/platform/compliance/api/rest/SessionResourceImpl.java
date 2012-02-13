package com.quova.platform.compliance.api.rest;

import com.quova.platform.compliance.dto.QSession;
import com.quova.platform.compliance.services.SessionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Controller
@Path("/sessions")
public class SessionResourceImpl extends BaseResource implements SessionResource {

  private static final Logger logger = Logger.getLogger(SessionResource.class);

  @Autowired
  private SessionService sessionService;

  @GET
  @Produces({MediaType.APPLICATION_XML})
  @Path("/new")
  public Response createSession() {
    Throwable t = null;
    Object o = null;

    try {
      QSession session = sessionService.createSession();
      o = session;
    } catch( Exception e ) {
      t = e;
    }

    return buildResponse( o, t );

  }

}

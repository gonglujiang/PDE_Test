package com.quova.platform.compliance.api.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.quova.platform.compliance.dto.QProxyData;
import com.quova.platform.compliance.dto.QSession;

@Path("/data")
public interface DataManagerResource {

    @POST
    @Consumes({MediaType.APPLICATION_XML})
    @Path("/{sessionId}")
    public void createData( @PathParam("sessionId") String sessionId, QProxyData proxyData );

    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Path("/retrieve")
    public QProxyData retrieveData( QSession session );

}

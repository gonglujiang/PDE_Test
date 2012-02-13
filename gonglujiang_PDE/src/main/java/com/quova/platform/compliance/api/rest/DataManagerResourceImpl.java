package com.quova.platform.compliance.api.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.quova.platform.compliance.dto.QProxyData;
import com.quova.platform.compliance.dto.QSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.quova.platform.compliance.services.DataManager;

@Controller
@Path("/data")
public class DataManagerResourceImpl extends BaseResource implements DataManagerResource {

    private static final Logger logger = Logger.getLogger(SessionResource.class);

    @Autowired
    private DataManager dataManager;

    @POST
    @Consumes({MediaType.APPLICATION_XML})
    @Path("/{sessionId}")
    public void createData( @PathParam("sessionId") String sessionId, QProxyData proxyData ) {
    //public void createData( @Context UriInfo uri, @PathParam("sessionId") String sessionId, QProxyData proxyData ) {
        QSession session = new QSession();
        session.setSessionId( sessionId );
        dataManager.storeData( session, proxyData );
    }

    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Path("/retrieve")
    public QProxyData retrieveData( QSession session ) {
        return new QProxyData();
    }

}

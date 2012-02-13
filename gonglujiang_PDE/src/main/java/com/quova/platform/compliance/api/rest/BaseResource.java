package com.quova.platform.compliance.api.rest;

import java.net.URI;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.quova.platform.compliance.api.rest.exception.DataFormatException;
import com.quova.platform.compliance.api.rest.exception.DataStoreException;
import com.quova.platform.compliance.dto.QSession;
import com.quova.platform.compliance.dto.QStatus;

/**
 * Created by IntelliJ IDEA.
 * User: skhoubyari
 * Date: Sep 24, 2010
 * Time: 3:00:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class BaseResource {

    private static final Logger logger = Logger.getLogger(BaseResource.class);

    public ApplicationContext appContext;

    public BaseResource() {
        appContext = new ClassPathXmlApplicationContext(new String[]{"application-context.xml"});
    }

    public ApplicationContext getContext() {
        return appContext;
    }

    protected Response buildResponse(Object o, Throwable e) throws WebApplicationException {
        return buildResponse(o, null, e);
    }

    protected Response buildResponse(Throwable e) throws WebApplicationException {
        return buildResponse(null, null, e);
    }

    protected Response buildResponse(Object o, URI contentLocation) throws WebApplicationException {
        return buildResponse(o, contentLocation, null);
    }

    protected Response buildResponse(Object o, URI contentLocation, Throwable e) throws WebApplicationException {
        boolean bErrored = false;
        Response.ResponseBuilder builder = null;

        if (null != e) {
            logger.warn("SERVICE Exception:", e);
            return buildExceptionResponse(e, o);
        }

        if (null == o) {
            logger.error("Can't build REST response for null object.");
            builder = Response.status(Response.Status.NOT_FOUND);
            //Todo serialize the error object & error code into the response.
            throw new WebApplicationException(builder.build());
        }

        if (!bErrored) {
            logger.debug("Building normal REST response.");
            builder = Response.ok(o);
            if (null != contentLocation) {
                builder.contentLocation(contentLocation);
            }
        }

        return builder.build();
    }

    // 404 IdentityNotFoundException
    // 409 ResourceConflictException and descendents
    // 400 DataFormatException
    // 500 DataStoreException and all other exceptions
    private Response buildExceptionResponse(Throwable e, Object o) {
        Response.Status httpStatus;
        String quovaCode;
        String description;
        quovaCode = e.getClass().getName(); // TODO: this may be TMI for the caller
        description = e.getMessage();  // the throwing module should be as specific as it can be. in case of 500 it may be TMI

        if (e instanceof DataFormatException) {
            httpStatus = Response.Status.BAD_REQUEST;
        } else {
            // includes DataStoreException and all other unmapped exceptions
            httpStatus = Response.Status.INTERNAL_SERVER_ERROR;
            if (!(e instanceof DataStoreException)) {
                description = "Unknown platform service failure"; // we are overriding the description to avoid TMI to the client
            }
        }
        QStatus qStatus = new QStatus();
        qStatus.setStatusCode(httpStatus.getStatusCode());
        qStatus.setSubCode(quovaCode);
        qStatus.setDescription(description);

        //Response.ResponseBuilder builder = builder = Response.status(httpStatus).header("X-quova-status:", "Error");
        Response.ResponseBuilder builder = Response.status(httpStatus);
        builder.entity(qStatus);
        return builder.build();
    }
}

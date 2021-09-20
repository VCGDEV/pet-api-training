package com.rest.training.controllers.exception;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.rest.training.exception.PetNotFoundException;

@Provider
public class PetNotFoundExceptionMapper implements ExceptionMapper<PetNotFoundException> {

    @Override
    public Response toResponse(PetNotFoundException exception) {
        Map<String, String> entity = new HashMap<>();
        entity.put(exception.getErrorCode().getCode(), exception.getErrorCode().getMessage());
        return Response
                    .status(Status.NOT_FOUND)
                    .entity(entity)
                    .build();
    }
    
}

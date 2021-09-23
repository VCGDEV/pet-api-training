package com.rest.training.controllers.exception;

import com.rest.training.exception.CategoryNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

@Provider
public class CategoryNotFoundExceptionMapper implements ExceptionMapper<CategoryNotFoundException> {
    @Override
    public Response toResponse(CategoryNotFoundException exception) {
        Map<String, String> entity = new HashMap<>();
        entity.put(exception.getErrorCode().getCode(), exception.getErrorCode().getMessage());
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(entity)
                .build();
    }
}

package com.rest.training.controllers.exception;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.rest.training.exception.ErrorCodes;

@Provider
public class ConstraintExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        List<ApiError> validationErrors = exception.getConstraintViolations()
                                            .stream()
                                            .map(c -> {
                                                ErrorCodes err = null;
                                                try {
                                                    err = ErrorCodes.fromCode(c.getMessage());
                                                } catch (IllegalArgumentException e) {
                                                    err = ErrorCodes.NOT_FOUND;
                                                }
                                                return new ApiError(err.getCode(), err.getMessage());
                                            })
                                            .collect(Collectors.toList()); 
        return Response.status(Status.BAD_REQUEST)
                .entity(validationErrors)
                .build();
    }
}

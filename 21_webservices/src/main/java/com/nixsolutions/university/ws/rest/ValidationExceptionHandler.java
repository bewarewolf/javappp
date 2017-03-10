package com.nixsolutions.university.ws.rest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionHandler implements ExceptionMapper<Throwable> {

  @Override
  public Response toResponse(Throwable e) {
    final StringBuilder strBuilder = new StringBuilder();
    
    if (e instanceof ValidationException) {
      for (ConstraintViolation<?> cv : ((ConstraintViolationException) e).getConstraintViolations()) {
        strBuilder.append(cv.getPropertyPath().toString() + " " + cv.getMessage());
      }
    } else {
      strBuilder.append(e.getMessage());
    }
    return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).type(MediaType.TEXT_PLAIN)
	.entity(strBuilder.toString()).build();
  }
}
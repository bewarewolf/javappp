package com.nixsolutions.university.ws.rest;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.nixsolutions.university.dto.PersonDTO;
import com.nixsolutions.university.service.PersonService;

@Path("person")
@Component
public class PersonRestService {

  @Autowired
  PersonService personService;
    
  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public PersonDTO getById(@Min(value = 1, message = "Person id must be greater than zero") 
      @PathParam("id") int id) {    
    PersonDTO out = personService.getById(id);
    if (out == null) {
      throw new NotFoundException("Person with id=" + id + " doesn't exist");
    }
    return out;
  } 
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<PersonDTO> list() {
    return personService.getAll();
  }
  
  @DELETE
  @Path("/{id}")
  public String delete(@Min(value = 1, message = "Person id must be greater than zero")
      @PathParam("id") int id) {
    personService.delete(id);
    return "success";
  }
  
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  public String create(@NotNull(message = "Person must not be null") 
      @RequestBody PersonDTO person) {
    return personService.saveOrUpdate(person).toString();
  }
  
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  public String update(@NotNull(message = "Person must not be null") 
      @RequestBody PersonDTO person) {
    personService.saveOrUpdate(person).toString();
    return "success";
  }  
}

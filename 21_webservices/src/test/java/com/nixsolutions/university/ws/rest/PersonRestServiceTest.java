package com.nixsolutions.university.ws.rest;

import org.springframework.web.context.ContextLoaderListener;

import com.nixsolutions.university.dto.PersonDTO;
import com.nixsolutions.university.ws.rest.PersonRestService;

import static org.junit.Assert.*;

import java.time.LocalDate;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.test.DeploymentContext;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.ServletDeploymentContext;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Test;

public class PersonRestServiceTest extends JerseyTest {

  @Override
  protected TestContainerFactory getTestContainerFactory() {
    return new GrizzlyWebTestContainerFactory();
  }

  @Override
  protected DeploymentContext configureDeployment() {
    return ServletDeploymentContext
	.forServlet(new ServletContainer(new ResourceConfig(PersonRestService.class)))
	.addListener(ContextLoaderListener.class)
	.contextParam("contextConfigLocation", "classpath:applicationContext.xml")
	.build();
  }

  @Test
  public void shouldGetListPersons() {
    // when
    Response response = target("person").request().get();
    
    // then
    assertEquals(200, response.getStatus());
    assertEquals("application/json", response.getHeaderString("Content-Type"));
  }
  
  @Test
  public void shouldGetPersonById() {
    // when
    Response response = target("person/1").request().get();
    
    // then
    assertEquals(200, response.getStatus());
    
    assertEquals("application/json", response.getHeaderString("Content-Type"));
  }
  
  @Test
  public void shouldDeletePersonById() {
    // when
    Response response = target("person/1").request().delete();
    
    // then
    assertEquals(200, response.getStatus());
    assertEquals("success", response.readEntity(String.class).toString());
  }
  
  @Test
  public void shouldCreatePerson() {
    // given
    PersonDTO pers = new PersonDTO("Averill1", 
	"Humphrey1", 
	"Samuelson1", 
	LocalDate.parse("1916-09-25"), 
	LocalDate.parse("2006-06-25"), 
	"root1", 
	"root1", 
	"Dean", 
	"Active");
    
    // when
    Response response = target("person").request().post(Entity.json(pers));
    
    // then
    assertEquals(200, response.getStatus());
    assertTrue(Integer.valueOf(response.readEntity(String.class).toString()) > 0);
  }
  
  @Test
  public void shouldUpdatePerson() {
    // given
    PersonDTO pers = new PersonDTO("Averill", 
	"Humphrey upd", 
	"Samuelson", 
	LocalDate.parse("1916-09-25"), 
	LocalDate.parse("2006-06-25"), 
	"root", 
	"root", 
	"Dean", 
	"Active");
    pers.setId(1);
    
    // when    
    Response response = target("person").request().put(Entity.json(pers));
    
    // then
    assertEquals(200, response.getStatus());
    assertEquals("success", response.readEntity(String.class).toString());
  }  
}
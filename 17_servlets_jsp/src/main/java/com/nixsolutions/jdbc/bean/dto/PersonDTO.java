package com.nixsolutions.jdbc.bean.dto;

import com.nixsolutions.jdbc.bean.Person;
import com.nixsolutions.jdbc.dao.DAOFactory;

public class PersonDTO {

  private Integer id;
  private String login;
  private String name;
  private String type;
  private String status;
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getLogin() {
    return login;
  }
  public void setLogin(String login) {
    this.login = login;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  @Override
  public String toString() {
    return "PersonDTO [id=" + id + ", login=" + login + ", name=" + name + ", type=" + type + ", status=" + status
	+ "]";
  }
  
  public PersonDTO(Integer id, String login, String firstName, String lastName, String type, String status) {
    super();
    this.id = id;
    this.login = login;
    this.name = firstName + " " + lastName;
    this.type = type;
    this.status = status;
  }  
  
  public PersonDTO(Person person) {
    this.id = person.getId();
    this.login = person.getLogin();
    this.name = person.getFirstName() + " " + person.getLastName();
    
    try {
      this.type = DAOFactory.getFactory().getPersonTypeDAO().getById(person.getPersonTypeId()).getValue();
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    } 
    try {
      this.status = DAOFactory.getFactory().getPersonStatusDAO().getById(person.getPersonStatusId()).getValue();
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}

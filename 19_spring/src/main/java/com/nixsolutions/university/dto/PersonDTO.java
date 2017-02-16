package com.nixsolutions.university.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.nixsolutions.university.model.Person;
import com.nixsolutions.university.model.PhoneNumber;

public class PersonDTO {

  private Integer id;
  private String login;
  private String password;
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }

  private String name;
  private String firstName;
  private String middleName;
  private String lastName;
  private LocalDate birthday;
  private LocalDate startDate;
  private String personType;
  private String personStatus;
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getMiddleName() {
    return middleName;
  }
  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public LocalDate getBirthday() {
    return birthday;
  }
  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }
  public LocalDate getStartDate() {
    return startDate;
  }
  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }
  public String getPersonType() {
    return personType;
  }
  public void setPersonType(String personType) {
    this.personType = personType;
  }
  public String getPersonStatus() {
    return personStatus;
  }
  public void setPersonStatus(String personStatus) {
    this.personStatus = personStatus;
  }
  public List<PhoneNumber> getPhoneNumbers() {
    return phoneNumbers;
  }
  public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
  }

  private List<PhoneNumber> phoneNumbers;
  
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
    if (name == null) {
      name = firstName + " " + lastName;
    }
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  
  @Override
  public String toString() {
    return "PersonDTO [id=" + id + ", login=" + login + ", name=" + name + ", type=" + personType + ", status=" + personStatus
	+ "]";
  }
  
  public PersonDTO() {  }  
  
  
  
  public PersonDTO(Person person) {
    super();
    this.id = person.getId();
    this.login = person.getLogin();
    this.firstName = person.getFirstName();
    this.middleName = person.getMiddleName();
    this.lastName = person.getLastName();
    this.birthday = person.getBirthday();
    this.startDate = person.getStartDate();
    this.personType = person.getPersonType().getValue();
    this.personStatus = person.getPersonStatus().getValue();
    this.phoneNumbers = person.getPhoneNumbers();
    this.name = firstName + " " + lastName; 
  }
  public static List<PersonDTO> convertFromPerson(List<Person> in) {
    List<PersonDTO> out = new ArrayList<>();
    in.forEach(p -> out.add(new PersonDTO(p)));
    return out;
  }
  
  
}

package com.nixsolutions.university.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.nixsolutions.university.model.Person;
import com.nixsolutions.university.model.PhoneNumber;

public class PersonDTO {

  private Integer id;
  private String login;
  private String password;
  private String name;
  private String firstName;
  private String middleName;
  private String lastName;
  private String birthday;
  private String startDate;
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

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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
    return "PersonDTO [id=" + id + ", login=" + login + ", name=" + name + ", type=" + personType + ", status="
	+ personStatus + "]";
  }

  public PersonDTO() {
  }

  public PersonDTO(Person person) {
    super();
    if (person != null) {
      this.id = person.getId();
      this.login = person.getLogin();
      this.password = person.getPassword();
      this.firstName = person.getFirstName();
      this.middleName = person.getMiddleName();
      this.lastName = person.getLastName();
      this.birthday = person.getBirthday().toString();
      this.startDate = person.getStartDate().toString();
      this.personType = person.getPersonType().getValue();
      this.personStatus = person.getPersonStatus().getValue();
      this.phoneNumbers = person.getPhoneNumbers();
      this.name = firstName + " " + lastName;
    }
  }

  public PersonDTO(String firstName, String middleName, String lastName, LocalDate birthday, LocalDate startDate,
      String login, String password, String personType, String personStatus) {
    this.login = login;
    this.password = password;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.birthday = birthday.toString();
    this.startDate = startDate.toString();
    this.personType = personType;
    this.personStatus = personStatus;
    this.name = firstName + " " + lastName;
  }
  
  public PersonDTO(String firstName, String middleName, String lastName, LocalDate birthday, LocalDate startDate,
      String login, String password, String personType, String personStatus, List<PhoneNumber> phoneNumbers) {
    this(firstName, middleName, lastName, birthday, startDate, login, password, personType, personStatus);
    this.phoneNumbers = phoneNumbers;
  }

  public static List<PersonDTO> convertFromPerson(List<Person> in) {
    return in.stream().map(p -> new PersonDTO(p)).collect(Collectors.toList());
  }

}

package com.nixsolutions.jdbc.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.nixsolutions.jdbc.util.LocalDateAttributeConverter;

@Entity
public class Person implements Serializable {

  private static final long serialVersionUID = -7363295378483616881L;

  @Id
  @Column(name = "person_id")
  private Integer id;
  @Column(name = "login")
  private String login;
  @Column(name = "password")
  private String password;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "middle_name")
  private String middleName;
  @Column(name = "last_name")
  private String lastName;
  @Column(name = "birthday", columnDefinition = "date")
  @Convert(converter = LocalDateAttributeConverter.class)
  private LocalDate birthday;
  @Column(name = "date_start", columnDefinition = "date")
  @Convert(converter = LocalDateAttributeConverter.class)
  private LocalDate startDate;
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "person_type_id", referencedColumnName = "person_type_id")
  private PersonType personType;
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "person_status_id", referencedColumnName = "person_status_id")
  private PersonStatus personStatus;
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "person_id", referencedColumnName = "person_id")
  private List<PhoneNumber> phoneNumbers;

  public Person() {
  }

  public Person(String firstName, String middleName, String lastName, LocalDate birthday, LocalDate startDate,
      PersonType personType, PersonStatus personStatus) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.birthday = birthday;
    this.startDate = startDate;
    this.personType = personType;
    this.personStatus = personStatus;
  }

  public Person(Integer id, String firstName, String middleName, String lastName, LocalDate birthday,
      LocalDate startDate, PersonType personType, PersonStatus personStatus) {
    this(firstName, middleName, lastName, birthday, startDate, personType, personStatus);
    this.id = id;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
    result = prime * result + ((personStatus == null) ? 0 : personStatus.hashCode());
    result = prime * result + ((personType == null) ? 0 : personType.hashCode());
    result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    Person other = (Person) obj;
    if (birthday == null) {
      if (other.birthday != null)
        return false;
    } else if (!birthday.equals(other.birthday))
      return false;
    if (firstName == null) {
      if (other.firstName != null)
        return false;
    } else if (!firstName.equals(other.firstName))
      return false;
    if (lastName == null) {
      if (other.lastName != null)
        return false;
    } else if (!lastName.equals(other.lastName))
      return false;
    if (middleName == null) {
      if (other.middleName != null)
        return false;
    } else if (!middleName.equals(other.middleName))
      return false;
    if (personStatus == null) {
      if (other.personStatus != null)
        return false;
    } else if (!personStatus.equals(other.personStatus))
      return false;
    if (personType == null) {
      if (other.personType != null)
        return false;
    } else if (!personType.equals(other.personType))
      return false;
    if (startDate == null) {
      if (other.startDate != null)
        return false;
    } else if (!startDate.equals(other.startDate))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Person [id=" + id + ", login=" + login + ", password=" + password + ", firstName=" + firstName
	+ ", middleName=" + middleName + ", lastName=" + lastName + ", birthday=" + birthday + ", startDate="
	+ startDate + ", personType=" + personType + ", personStatus=" + personStatus + ", phoneNumbers=" + phoneNumbers
	+ "]";
  }

  public PersonType getPersonType() {
    return personType;
  }

  public void setPersonType(PersonType personType) {
    this.personType = personType;
  }

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

  public PersonStatus getPersonStatus() {
    return personStatus;
  }

  public void setPersonStatus(PersonStatus personStatus) {
    this.personStatus = personStatus;
  }

  
  public List<PhoneNumber> getPhoneNumbers() {
    return phoneNumbers;
  }

  
  public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
  }

}

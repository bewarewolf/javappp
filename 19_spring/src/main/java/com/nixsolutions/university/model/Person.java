package com.nixsolutions.university.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.nixsolutions.university.util.LocalDateAttributeConverter;

@Entity
public class Person implements Serializable {

  private static final long serialVersionUID = -7363295378483616881L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "person_id")
  private Integer id;
  
  @Column(name = "login", unique = true)
  @NotNull
  @Size(max = 100)
  private String login;
  
  @Column(name = "password")
  @NotNull
  @Size(max = 200)
  private String password;
  
  @Column(name = "first_name")
  @NotNull
  @Size(max = 100)
  private String firstName;
  
  @Column(name = "middle_name")
  @Size(max = 100)
  private String middleName;
  
  @Column(name = "last_name")
  @NotNull
  @Size(max = 100)
  private String lastName;
  
  @Formula("first_name || ' ' || last_name")
  private String fullName;
  
  @Column(name = "birthday", columnDefinition = "date")
  @Convert(converter = LocalDateAttributeConverter.class)
  @NotNull
  private LocalDate birthday;
  
  @Column(name = "date_start", columnDefinition = "date")
  @Convert(converter = LocalDateAttributeConverter.class)
  @NotNull
  private LocalDate startDate;
  
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "person_type_id", referencedColumnName = "person_type_id")
  @NotNull
  private PersonType personType;
  
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "person_status_id", referencedColumnName = "person_status_id")
  @NotNull
  private PersonStatus personStatus;
  
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  @JoinColumn(name = "person_id", referencedColumnName = "person_id")
  private List<PhoneNumber> phoneNumbers;

  @OneToMany(mappedBy="teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  @OnDelete(action=OnDeleteAction.CASCADE)
  private List<Subject> subjects;
  
  @OneToMany(mappedBy="person", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  @OnDelete(action=OnDeleteAction.CASCADE)
  private List<Journal> grades;
  
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
  
  public Person(String firstName, String middleName, String lastName, LocalDate birthday, LocalDate startDate,
      PersonType personType, PersonStatus personStatus, String login, String password) {
    this(firstName, middleName, lastName, birthday, startDate, personType, personStatus);
    this.login = login;
    this.password = password;
  }

  public Person(Integer id, String firstName, String middleName, String lastName, LocalDate birthday,
      LocalDate startDate, PersonType personType, PersonStatus personStatus) {
    this(firstName, middleName, lastName, birthday, startDate, personType, personStatus);
    this.id = id;
  }
  
  public Person(Integer id, String firstName, String middleName, String lastName, LocalDate birthday,
      LocalDate startDate, PersonType personType, PersonStatus personStatus, String login, String password) {
    this(firstName, middleName, lastName, birthday, startDate, personType, personStatus, login, password);
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

  public String getFullName() {
    return fullName;
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
    if (this.phoneNumbers == null) {
      this.phoneNumbers = new ArrayList<>();
    }
    return phoneNumbers;
  }

  
  public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
    if (this.phoneNumbers == null) {
      this.phoneNumbers = phoneNumbers;
    } else {
      this.phoneNumbers.retainAll(phoneNumbers);
      this.phoneNumbers.addAll(phoneNumbers);
    }
  }

  public boolean isEnabled() {
    return this.personStatus.getValue().equals("Active");
  }
}

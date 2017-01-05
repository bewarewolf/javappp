package com.nixsolutions.jdbc.bean;

import java.time.LocalDate;
import java.util.List;

public class Person extends AbstractBean {

  private static final long serialVersionUID = -7363295378483616881L;
  
  private String firstName;
  private String middleName;
  private String lastName;
  private LocalDate birthday;
  private LocalDate startDate;
  private PersonType personType;
  private PersonStatus personStatus;
  private List<PhoneNumber> phoneNumbers;
  
  public Person() {}
  
  public Person(String firstName, String middleName, String lastName, 
      LocalDate birthday, LocalDate startDate, PersonType personType, 
      PersonStatus personStatus, List<PhoneNumber> phoneNumbers) {
    super();
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.birthday = birthday;
    this.startDate = startDate;
    this.personType = personType;
    this.personStatus = personStatus;
    this.phoneNumbers = phoneNumbers;
  }
  
  public Person(Integer id, String firstName, String middleName, String lastName, 
      LocalDate birthday, LocalDate startDate, PersonType personType, 
      PersonStatus personStatus, List<PhoneNumber> phoneNumbers) {
    this(firstName, middleName, lastName, birthday, startDate, personType, personStatus, phoneNumbers);
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }
  
  public void setFirstName(String firstName) {
    if (firstName != null) {
      this.firstName = firstName;
    } else {
      throw new RuntimeException("First name should not be null");
    }
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
    if (lastName != null) {
      this.lastName = lastName;
    } else {
      throw new RuntimeException("Last name should not be null");
    }
  }
  
  public LocalDate getBirthday() {
    return birthday;
  }
  
  public void setBirthday(LocalDate birthday) {
    if (birthday != null) {
      this.birthday = birthday;
    } else {
      throw new RuntimeException("Birthday should not be null");
    }
  }
  
  public LocalDate getStartDate() {
    return startDate;
  }
  
  public void setStartDate(LocalDate startDate) {
    if (startDate != null) {
      this.startDate = startDate;
    } else {
      throw new RuntimeException("Start date should not be null");
    }
  }
  
  public PersonType getPersonType() {
    return personType;
  }
  
  public void setPersonType(PersonType personType) {
    if (personType != null) {
      this.personType = personType;
    } else {
      throw new RuntimeException("Person type should not be null");
    }
  }
  
  public PersonStatus getPersonStatus() {
    return personStatus;
  }
  
  public void setPersonStatus(PersonStatus personStatus) {
    if (personStatus != null) {
      this.personStatus = personStatus;
    } else {
      throw new RuntimeException("Person status should not be null");
    }
  }

  public List<PhoneNumber> getPhoneNumbers() {
    return phoneNumbers;
  }

  public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
  }
  
  public String getFullName() {
    StringBuilder sb = new StringBuilder(firstName);
    
    if (middleName != null) {
      sb.append(" ");
      sb.append(middleName);
    }
    
    sb.append(" ");
    sb.append(lastName);
    
    return sb.toString();
  }

  @Override
  public String toString() {
    return "Person [id=" + id + ", firstName=" + firstName + ", middleName=" + middleName 
	+ ", lastName=" + lastName + ", birthday=" + birthday + ", startDate=" + startDate 
	+ ", personType=" + personType + ", personStatus=" + personStatus
	+ ", phoneNumbers=" + phoneNumbers + "]";
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
    result = prime * result + ((phoneNumbers == null) ? 0 : phoneNumbers.hashCode());
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
    if (phoneNumbers == null) {
      if (other.phoneNumbers != null)
	return false;
    } else if (!phoneNumbers.equals(other.phoneNumbers))
      return false;
    if (startDate == null) {
      if (other.startDate != null)
	return false;
    } else if (!startDate.equals(other.startDate))
      return false;
    return true;
  }
  
  
}

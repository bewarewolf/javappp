package com.nixsolutions.jdbc.dao;

import com.nixsolutions.jdbc.bean.Person;
import com.nixsolutions.jdbc.bean.PersonStatus;
import com.nixsolutions.jdbc.bean.PersonType;
import com.nixsolutions.jdbc.bean.PhoneNumber;

public interface PersonDAO extends GenericDAO<Person> {

  Person getByName(String firstName, String lastName);
  boolean setPersonType(Person p, PersonType t);
  boolean setPersonStatus(Person p, PersonStatus t);
  boolean addPhoneNumber(Person p, String number);
  boolean addPhoneNumber(PhoneNumber pn);
  boolean removePhoneNumber(PhoneNumber pn);
}

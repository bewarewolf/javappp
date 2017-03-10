package com.nixsolutions.university.dao;

import com.nixsolutions.university.model.PersonType;

public interface PersonTypeDAO extends GenericDAO<PersonType> {

  PersonType getByValue(String value);
}

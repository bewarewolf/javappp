package com.nixsolutions.jdbc.dao;

import com.nixsolutions.jdbc.bean.PersonType;

public interface PersonTypeDAO extends GenericDAO<PersonType> {

  PersonType getByValue(String value);
}

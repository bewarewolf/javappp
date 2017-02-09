package com.nixsolutions.university.dao;

import com.nixsolutions.university.model.PersonStatus;

public interface PersonStatusDAO extends GenericDAO<PersonStatus> {

  PersonStatus getByValue(String value);
}

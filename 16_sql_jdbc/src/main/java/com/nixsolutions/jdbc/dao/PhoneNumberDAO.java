package com.nixsolutions.jdbc.dao;

import java.util.List;

import com.nixsolutions.jdbc.bean.PhoneNumber;

public interface PhoneNumberDAO extends GenericDAO<PhoneNumber> {

  List<PhoneNumber> getByPersonId(Integer id);
  boolean deleteByPersonId(Integer id);
}

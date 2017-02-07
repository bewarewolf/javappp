package com.nixsolutions.jdbc.dao;

import java.util.List;

import com.nixsolutions.jdbc.bean.PhoneNumber;

public interface PhoneNumberDAO extends GenericDAO<PhoneNumber> {

  PhoneNumber getByNumber(String number);
  List<PhoneNumber> getByPersonId(Integer id);
  void deleteByPersonId(Integer id);
}

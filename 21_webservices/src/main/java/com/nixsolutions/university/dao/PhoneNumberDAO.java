package com.nixsolutions.university.dao;

import java.util.List;

import com.nixsolutions.university.model.PhoneNumber;

public interface PhoneNumberDAO extends GenericDAO<PhoneNumber> {

  PhoneNumber getByNumber(String number);
  List<PhoneNumber> getByPersonId(Integer id);
  void deleteByPersonId(Integer id);
}

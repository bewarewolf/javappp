package com.nixsolutions.jdbc.dao;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.jdbc.bean.PhoneNumber;

public interface PhoneNumberDAO extends GenericDAO<PhoneNumber> {

  List<PhoneNumber> getByPersonId(Integer id) throws SQLException;
  boolean deleteByPersonId(Integer id) throws SQLException;
}

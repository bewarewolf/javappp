package com.nixsolutions.jdbc.dao;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.jdbc.bean.User;

public interface UserDAO extends GenericDAO<User> {

  User getByLogin(String login) throws SQLException ;
  List<User> getByRoleId(Integer roleId) throws SQLException ;
}

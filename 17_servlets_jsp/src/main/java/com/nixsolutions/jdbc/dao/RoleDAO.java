package com.nixsolutions.jdbc.dao;

import java.sql.SQLException;

import com.nixsolutions.jdbc.bean.Role;

public interface RoleDAO extends GenericDAO<Role> {
 
  Role getByName(String name) throws SQLException ;
}

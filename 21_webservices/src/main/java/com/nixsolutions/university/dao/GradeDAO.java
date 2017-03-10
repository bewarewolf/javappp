package com.nixsolutions.university.dao;

import com.nixsolutions.university.model.Grade;

public interface GradeDAO extends GenericDAO<Grade> {

  Grade getByDescription(String description);
}

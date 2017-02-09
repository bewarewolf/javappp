package com.nixsolutions.university.dao;

import java.util.List;

import com.nixsolutions.university.model.Subject;

public interface SubjectDAO extends GenericDAO<Subject> {

  Subject getByName(String name);
  List<Subject> getByTeacherId(Integer teacherId);
  List<Subject> getBySemesterId(Integer semesterId);
}

package com.nixsolutions.jdbc.dao;

import java.util.List;

import com.nixsolutions.jdbc.bean.Person;
import com.nixsolutions.jdbc.bean.Semester;
import com.nixsolutions.jdbc.bean.Subject;

public interface SubjectDAO extends GenericDAO<Subject> {

  List<Subject> getByTeacher(Person teacher);
  List<Subject> getByTeacherId(Integer teacherId);
  List<Subject> getBySemester(Semester semester);
  List<Subject> getBySemesterId(Integer semesterId);
}

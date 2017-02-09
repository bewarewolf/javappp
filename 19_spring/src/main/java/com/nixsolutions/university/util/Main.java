package com.nixsolutions.university.util;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nixsolutions.university.dao.JournalDAO;
import com.nixsolutions.university.dao.PersonDAO;
import com.nixsolutions.university.dao.SemesterDAO;
import com.nixsolutions.university.dao.SubjectDAO;
import com.nixsolutions.university.model.Semester;
import com.nixsolutions.university.model.Subject;


public class Main {
  
  public static void main(String[] args) throws Exception {
    ApplicationContext c = new ClassPathXmlApplicationContext("spring-context.xml");
//    SemesterDAO p = (SemesterDAO) c.getBean("semesterDao");
//    Semester s = p.getById(1);
//    List<Subject> l = s.getSubjects();
//    System.out.println("person: " + s);
//    p.delete(1);
//    System.out.println("person: " + p.getById(1));
    
    SubjectDAO p = (SubjectDAO) c.getBean("subjectDao");
    Subject s = p.getById(2);
    System.out.println(s);
    p.delete(2);
    
    
  }

}
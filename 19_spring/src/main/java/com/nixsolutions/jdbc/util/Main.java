package com.nixsolutions.jdbc.util;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nixsolutions.jdbc.bean.Semester;
import com.nixsolutions.jdbc.bean.Subject;
import com.nixsolutions.jdbc.dao.JournalDAO;
import com.nixsolutions.jdbc.dao.PersonDAO;
import com.nixsolutions.jdbc.dao.SemesterDAO;
import com.nixsolutions.jdbc.dao.SubjectDAO;


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
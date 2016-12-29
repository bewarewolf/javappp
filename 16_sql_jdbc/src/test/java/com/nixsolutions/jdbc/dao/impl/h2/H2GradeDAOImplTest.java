package com.nixsolutions.jdbc.dao.impl.h2;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.nixsolutions.jdbc.bean.Grade;

@RunWith(JUnit4.class)
public class H2GradeDAOImplTest {

  @Test
  public void test1() {
    Grade g1 = new Grade();
    g1.setDescription("Very good");
    g1.setValue(5);
    
    Grade g2 = new Grade();
    g2.setDescription("Good");
    g2.setValue(4);
    
    Grade g3 = new Grade();
    g3.setDescription("sg");
    g3.setValue(2);
    
    H2GradeDAOImpl dao = new H2GradeDAOImpl();
    
//    dao.create(g1);
//    dao.create(g3);
    
    dao.delete(5);
    
    List<Grade> l = dao.getAll();
    
    for (Grade g : l) {
      System.out.println(g);
    }
  }
}

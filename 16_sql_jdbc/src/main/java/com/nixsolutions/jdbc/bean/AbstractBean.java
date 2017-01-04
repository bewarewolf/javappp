package com.nixsolutions.jdbc.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class AbstractBean implements Serializable {

  protected Integer id;
  
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer gradeId) {
    this.id = gradeId;
  }
}

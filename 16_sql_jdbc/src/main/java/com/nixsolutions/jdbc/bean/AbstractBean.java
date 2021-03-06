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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AbstractBean other = (AbstractBean) obj;
    if (id == null) {
      if (other.id != null)
	return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
}

package com.nixsolutions.jdbc.bean;

public class Role extends AbstractBean {

  /**
   * 
   */
  private static final long serialVersionUID = -2750547304018855671L;

  private String roleName;

  public Role() {}
  
  public Role(String roleName) {
    super();
    this.roleName = roleName;
  }

  public Role(Integer id, String roleName) {
    super();
    this.roleName = roleName;
    this.id = id;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    Role other = (Role) obj;
    if (roleName == null) {
      if (other.roleName != null)
	return false;
    } else if (!roleName.equals(other.roleName))
      return false;
    return true;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "Role [id=" + id + ", roleName=" + roleName + "]";
  }
}

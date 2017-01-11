package com.nixsolutions.jdbc.bean;

public class User extends AbstractBean {

  /**
   * 
   */
  private static final long serialVersionUID = 5071757952123661731L;

  private Integer personId;
  private String login;
  private String password;
  private Integer roleId;
  
  public User(Integer personId, String login, String password, Integer roleId) {
    this.personId = personId;
    this.login = login;
    this.password = password;
    this.roleId = roleId;
  }
  
  public User(Integer id, Integer personId, String login, String password, Integer roleId) {
    this(personId, login, password, roleId);
    this.id = id;
  }

  public User() {}

  @Override
  public String toString() {
    return "User [id=" + id + "personId=" + personId + ", login=" + login 
	+ ", password=" + password + ", roleId=" + roleId + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((login == null) ? 0 : login.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + ((personId == null) ? 0 : personId.hashCode());
    result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
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
    User other = (User) obj;
    if (login == null) {
      if (other.login != null)
	return false;
    } else if (!login.equals(other.login))
      return false;
    if (password == null) {
      if (other.password != null)
	return false;
    } else if (!password.equals(other.password))
      return false;
    if (personId == null) {
      if (other.personId != null)
	return false;
    } else if (!personId.equals(other.personId))
      return false;
    if (roleId == null) {
      if (other.roleId != null)
	return false;
    } else if (!roleId.equals(other.roleId))
      return false;
    return true;
  }

  public Integer getPersonId() {
    return personId;
  }

  public void setPersonId(Integer personId) {
    this.personId = personId;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }
}

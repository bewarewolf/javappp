package com.nixsolutions.jsp.servlet.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.nixsolutions.jdbc.bean.PersonStatus;
import com.nixsolutions.jdbc.bean.PersonType;

public class DAOUtils {

  public static List<PersonStatus> psList = new ArrayList<>();
  public static List<PersonType> ptList = new ArrayList<>();
  
  public static void setPersonTypes(Collection<? extends PersonType> coll) {
    ptList.addAll(coll);
  }
  
  public static void setPersonStatuses(Collection<? extends PersonStatus> coll) {
    psList.addAll(coll);
  }
  
  public PersonStatus getPersonStatusById(Integer id) {
    for (PersonStatus ps : psList) {
      if (ps.getId().equals(id)) {
	return ps;
      }
    }
    
    return null;
  }
  
  public PersonStatus getPersonStatusByValue(String value) {
    for (PersonStatus ps : psList) {
      if (ps.getValue().equals(value)) {
	return ps;
      }
    }
    
    return null;
  }
  
  public PersonType getPersonTypeById(Integer id) {
    for (PersonType pt : ptList) {
      if (pt.getId().equals(id)) {
	return pt;
      }
    }
    
    return null;
  }
  
  public PersonType getPersonTypeByValue(String value) {
    for (PersonType pt : ptList) {
      if (pt.getValue().equals(value)) {
	return pt;
      }
    }
    
    return null;
  }
  
  public List<PersonStatus> getPsList() {
    return psList;
  }
  
  public List<PersonType> getPtList() {
    return ptList;
  }
}

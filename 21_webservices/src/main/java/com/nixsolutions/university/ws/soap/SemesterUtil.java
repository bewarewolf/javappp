package com.nixsolutions.university.ws.soap;

import java.time.ZoneId;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import com.nixsolutions.university.ws.soap.semester.ObjectFactory;
import com.nixsolutions.university.ws.soap.semester.Semester;

public class SemesterUtil {

  private static final ObjectFactory objectFactory = new ObjectFactory();
  
  public static ObjectFactory getObjectFactory() {
    return objectFactory;
  }
  
  public static Semester convertFromModel(com.nixsolutions.university.model.Semester in) {
    Semester out = objectFactory.createSemester();
    out.setId(in.getId().toString());
    out.setSemesterName(in.getSemesterName());
    
    GregorianCalendar startGregorian = GregorianCalendar.from(
	in.getStartDate().atStartOfDay(ZoneId.systemDefault()));
    try {
      out.setStartDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(startGregorian));
    } catch (DatatypeConfigurationException ex) {
      throw new RuntimeException(ex);
    }
   
    GregorianCalendar endGregorian = GregorianCalendar.from(
	in.getEndDate().atStartOfDay(ZoneId.systemDefault()));
    try {
      out.setEndDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(endGregorian));
    } catch (DatatypeConfigurationException ex) {
      throw new RuntimeException(ex);
    }
    return out;
  }
  
  public static com.nixsolutions.university.model.Semester convertToModel(Semester in) {
    com.nixsolutions.university.model.Semester out = new com.nixsolutions.university.model.Semester();
    out.setId(Integer.valueOf(in.getId()));
    out.setSemesterName(in.getSemesterName());
    out.setStartDate(in.getStartDate().toGregorianCalendar().toZonedDateTime().toLocalDate());
    out.setEndDate(in.getEndDate().toGregorianCalendar().toZonedDateTime().toLocalDate());
    return out;
  }
}

package com.nixsolutions.jsp.servlet.subject;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.jdbc.bean.Person;
import com.nixsolutions.jdbc.bean.Semester;
import com.nixsolutions.jdbc.bean.Subject;
import com.nixsolutions.jdbc.bean.dto.PersonDTO;
import com.nixsolutions.jdbc.bean.dto.SubjectDTO;
import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.PersonDAO;
import com.nixsolutions.jdbc.dao.SemesterDAO;
import com.nixsolutions.jdbc.dao.SubjectDAO;
import com.nixsolutions.jsp.servlet.utils.DAOUtils;

@WebServlet("/admin/addSubject")
public class AddSubjectServlet extends HttpServlet{

  /**
   * 
   */
  private static final long serialVersionUID = -4534299119788961527L;
  
  private static final Logger LOG = LogManager.getLogger();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      DAOFactory fact = DAOFactory.getFactory();
      SemesterDAO semDao = fact.getSemesterDAO();
      PersonDAO persDao = fact.getPersonDAO();
            
      List<Person> teachers = persDao.getByType(new DAOUtils().getPersonTypeByValue("Teacher").getId());
      
      List<PersonDTO> dtoTeachers = new ArrayList<>();
      
      for (Person p : teachers) {
	dtoTeachers.add(new PersonDTO(p.getId(), p.getLogin(), p.getFirstName(), p.getLastName(), 
	    new DAOUtils().getPersonTypeById(p.getPersonTypeId()).getValue(), 
	    new DAOUtils().getPersonStatusById(p.getPersonStatusId()).getValue()));
      }
      
      List<Semester> semesters = semDao.getAll();
      semesters.removeIf(s -> s.getEndDate().isBefore(LocalDate.now()));
      
      req.setAttribute("teachers", dtoTeachers);
      req.setAttribute("semesters", semesters);
      req.getRequestDispatcher("/WEB-INF/jsp/subject/add_subject.jsp").forward(req, resp);
    } catch (Exception ex) {
      LOG.error(ex);
      throw new ServletException(ex);
    }
  }
}

package com.nixsolutions.jsp.servlet.person;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.h2.util.StringUtils;

import com.nixsolutions.jdbc.bean.Person;
import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.PersonDAO;

@WebServlet("/admin/processPerson")
public class InsertPersonServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = -3891545895807969257L;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Person pers = new Person(); 
    
    String id = req.getParameter("personId");
    if (!StringUtils.isNullOrEmpty(id)) {
      pers.setId(Integer.valueOf(id));
    }
    pers.setLogin(req.getParameter("login"));
    pers.setPassword(req.getParameter("password"));    
    pers.setFirstName(req.getParameter("fName"));
    pers.setMiddleName(req.getParameter("mName"));
    pers.setLastName(req.getParameter("lName"));
    pers.setBirthday(LocalDate.parse(req.getParameter("birthday"), DateTimeFormatter.ISO_LOCAL_DATE));
    pers.setStartDate(LocalDate.parse(req.getParameter("startDate"), DateTimeFormatter.ISO_LOCAL_DATE));        
    
    String pt = req.getParameter("pt");
    String ps = req.getParameter("ps");
    pers.setPersonTypeId(Integer.valueOf(pt));
    pers.setPersonStatusId(Integer.valueOf(ps));
    
    try {
      DAOFactory fact = DAOFactory.getFactory();
      PersonDAO personDao = fact.getPersonDAO();
      
      if (pers.getId() != null) {
	personDao.update(pers);
      } else {
	personDao.create(pers);
      }
      
      resp.sendRedirect(req.getContextPath() + "/admin/persons");
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
  } 
}

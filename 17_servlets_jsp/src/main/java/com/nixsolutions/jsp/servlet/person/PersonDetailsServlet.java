package com.nixsolutions.jsp.servlet.person;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.h2.util.StringUtils;

import com.nixsolutions.jdbc.bean.Person;
import com.nixsolutions.jdbc.bean.PhoneNumber;
import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.PersonDAO;
import com.nixsolutions.jdbc.dao.PhoneNumberDAO;

@WebServlet("/admin/personDetails")
public class PersonDetailsServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = -871764955992719715L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String userId = req.getParameter("userId");

    try {
      if (!StringUtils.isNullOrEmpty(userId)) {
	DAOFactory fact = DAOFactory.getFactory();
	PersonDAO personDao = fact.getPersonDAO();

	Person pers = personDao.getById(Integer.valueOf(req.getParameter("userId")));
	req.setAttribute("person", pers);

	PhoneNumberDAO phoneDao = fact.getPhoneNumberDAO();
	List<PhoneNumber> phones = phoneDao.getByPersonId(pers.getId());
	req.setAttribute("phones", phones);
      }
      
      req.getRequestDispatcher("/WEB-INF/jsp/person/add_person.jsp").forward(req, resp);
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

  }
}

package com.nixsolutions.jsp.servlet.person;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nixsolutions.jdbc.bean.Person;
import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.PersonDAO;

@WebServlet("/admin/persons")
public class PersonListServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = -5504522029515327880L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      DAOFactory fact = DAOFactory.getFactory();
      PersonDAO personDao = fact.getPersonDAO();

      List<Person> pList = personDao.getAll();
      
      HttpSession ses = req.getSession(false);
      Person curUser = (Person) ses.getAttribute("user");
      pList.removeIf(p -> p.equals(curUser));
      
      req.setAttribute("persons", pList);

      req.getRequestDispatcher("/WEB-INF/jsp/person/person_list.jsp").forward(req, resp);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
}

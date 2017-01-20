package com.nixsolutions.jsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nixsolutions.jdbc.bean.Person;
import com.nixsolutions.jdbc.bean.PersonType;
import com.nixsolutions.jdbc.bean.Role;
import com.nixsolutions.jdbc.bean.User;
import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.PersonDAO;
import com.nixsolutions.jdbc.dao.PersonTypeDAO;
import com.nixsolutions.jdbc.dao.RoleDAO;
import com.nixsolutions.jdbc.dao.UserDAO;
import com.nixsolutions.jsp.servlet.utils.Utils;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = 6672177343012691266L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {    
    String entity = req.getParameter("entity");    
    
    switch (entity) {
    case "Student":
    case "Teacher":
      processPerson(req, resp, entity);
      break;
    case "Subject":
      break;
    case "Journal":
      break;
      default:
	req.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
    }    
  }  
  
  private void processPerson(HttpServletRequest req, HttpServletResponse resp, String entity) {
    try {
      DAOFactory fact = DAOFactory.getFactory();
      PersonTypeDAO ptDao = fact.getPersonTypeDAO();
      PersonType pt = ptDao.getByValue(entity);
      
      PersonDAO personDao = fact.getPersonDAO();
      
      List<Person> pList = personDao.getByType(pt.getId());
      
      req.setAttribute("persons", pList);
      
      req.getRequestDispatcher("/WEB-INF/jsp/admin/person_list.jsp").forward(req, resp);
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

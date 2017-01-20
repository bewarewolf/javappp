package com.nixsolutions.jsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.jdbc.bean.Person;
import com.nixsolutions.jdbc.bean.PersonStatus;
import com.nixsolutions.jdbc.bean.PersonType;
import com.nixsolutions.jdbc.bean.Role;
import com.nixsolutions.jdbc.bean.User;
import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.PersonDAO;
import com.nixsolutions.jdbc.dao.PersonStatusDAO;
import com.nixsolutions.jdbc.dao.PersonTypeDAO;
import com.nixsolutions.jdbc.dao.RoleDAO;
import com.nixsolutions.jdbc.dao.UserDAO;
import com.nixsolutions.jsp.servlet.utils.Utils;

@WebServlet("/admin/editPerson")
public class EditPersonServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = -871764955992719715L;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      DAOFactory fact = DAOFactory.getFactory();
      PersonDAO personDao = fact.getPersonDAO();
      
      Person pers = personDao.getById(Integer.valueOf(req.getParameter("userId"))); 
      req.setAttribute("person", pers);
          
      PersonTypeDAO ptDao = fact.getPersonTypeDAO();
      PersonType pt = ptDao.getById(pers.getPersonTypeId());
      req.setAttribute("type", pt.getValue());
            
      PersonStatusDAO statDao = fact.getPersonStatusDAO();
      PersonStatus ps = statDao.getById(pers.getPersonStatusId());
      req.setAttribute("status", ps.getValue());
      
      req.getRequestDispatcher("/WEB-INF/jsp/admin/add_person.jsp").forward(req, resp);
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
    
  }
}

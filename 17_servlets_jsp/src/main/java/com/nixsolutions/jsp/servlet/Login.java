package com.nixsolutions.jsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.jdbc.bean.Role;
import com.nixsolutions.jdbc.bean.User;
import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.RoleDAO;
import com.nixsolutions.jdbc.dao.UserDAO;

@WebServlet("/login")
public class Login extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = 1178731295782927094L;
  
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     
    String login = req.getParameter("login");
    String password = req.getParameter("password");
    
    PrintWriter writer = resp.getWriter();
    
    try {
      DAOFactory fact = DAOFactory.getFactory();    
      UserDAO usrDao = fact.getUserDAO();
      
      User user = usrDao.getByLogin(login);
      
      if (user == null || !user.getPassword().equals(password)) {
	writer.write("<h2>Invalid login or password</h2><br />");
	writer.write("<a href=\"index.html\">Home</a>");
	return;
      } 
      
      RoleDAO roleDao = fact.getRoleDAO();
      Role r = roleDao.getById(user.getRoleId());
      
      if ("Admin".equals(r.getRoleName())) {
	writer.write("<h2>Welcome, " + user.getLogin() + "</h2><br />");
	writer.write("<a href=\"index.html\">Home</a>");
      } else if ("User".equals(r.getRoleName())) {
	writer.write("<h2>Welcome, " + user.getLogin() + "</h2><br />");
	writer.write("<a href=\"index.html\">Home</a>");
      }
      
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
  }
}

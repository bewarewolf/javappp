package com.nixsolutions.jsp.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.jdbc.bean.Person;
import com.nixsolutions.jdbc.bean.PersonType;
import com.nixsolutions.jdbc.bean.Role;
import com.nixsolutions.jdbc.bean.User;
import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.PersonDAO;
import com.nixsolutions.jdbc.dao.PersonStatusDAO;
import com.nixsolutions.jdbc.dao.PersonTypeDAO;
import com.nixsolutions.jdbc.dao.RoleDAO;
import com.nixsolutions.jdbc.dao.UserDAO;
import com.nixsolutions.jsp.servlet.utils.DAOUtils;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = 1178731295782927094L;

  private static final Logger LOG = LogManager.getLogger();
    
  @Override
  public void init() throws ServletException {
    super.init();
    
    try {
      DAOFactory fact = DAOFactory.getFactory();
      PersonTypeDAO ptDao = fact.getPersonTypeDAO();
      PersonStatusDAO psDao = fact.getPersonStatusDAO();
      
      DAOUtils.setPersonStatuses(psDao.getAll());
      DAOUtils.setPersonTypes(ptDao.getAll());
    } catch (Exception e) {
      throw new ServletException(e);
    } 
  }
  
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String login = req.getParameter("login");
    String password = req.getParameter("password");
    
    try {
      DAOFactory fact = DAOFactory.getFactory();
      PersonDAO usrDao = fact.getPersonDAO();

      Person user = usrDao.getByCredentials(login, password);

      if (user == null) {
	RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
	PrintWriter out = resp.getWriter();
	out.println("<font color=red>Either user name or password is wrong.</font>");
	rd.include(req, resp);
      } else {

	PersonTypeDAO roleDao = fact.getPersonTypeDAO();
	PersonType r = roleDao.getById(user.getPersonTypeId());
	
	HttpSession ses = req.getSession(true);
	ses.setAttribute("user", user);
	ses.setAttribute("role", r.getValue());
	ses.setMaxInactiveInterval(30 * 60);
	
	req.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
      }
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
  }
}

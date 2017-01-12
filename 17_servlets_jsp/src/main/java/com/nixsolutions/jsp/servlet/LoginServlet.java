package com.nixsolutions.jsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.jdbc.bean.Role;
import com.nixsolutions.jdbc.bean.User;
import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.RoleDAO;
import com.nixsolutions.jdbc.dao.UserDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = 1178731295782927094L;

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String login = req.getParameter("login");
    String password = req.getParameter("password");
    
    try {
      DAOFactory fact = DAOFactory.getFactory();
      UserDAO usrDao = fact.getUserDAO();

      User user = usrDao.getByLogin(login);

      if (user == null || !user.getPassword().equals(password)) {
	RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
	PrintWriter out = resp.getWriter();
	out.println("<font color=red>Either user name or password is wrong.</font>");
	rd.include(req, resp);
      } else {

	RoleDAO roleDao = fact.getRoleDAO();
	Role r = roleDao.getById(user.getRoleId());

	Cookie loginCookie = new Cookie("user", user.getLogin());
	loginCookie.setMaxAge(10 * 60);
	Cookie roleCookie = new Cookie("role", r.getRoleName());
	roleCookie.setMaxAge(10 * 60);
	
	resp.addCookie(loginCookie);
	resp.addCookie(roleCookie);
	
	resp.sendRedirect("landing");
      }
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
  }
}

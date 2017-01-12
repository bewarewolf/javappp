package com.nixsolutions.jsp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.jdbc.bean.User;
import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.UserDAO;
import com.nixsolutions.jsp.servlet.utils.Utils;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = -871764955992719715L;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String userName = Utils.getCookies(req, resp).get("user");

    if (userName == null) {
      return;
    }
    
    User user = new User(Integer.valueOf(req.getParameter("userid")),
	null,
	req.getParameter("login"),
	req.getParameter("password"),
	Integer.valueOf(req.getParameter("roles")));
    
    try {
      DAOFactory fact = DAOFactory.getFactory();
      UserDAO userDao = fact.getUserDAO();
      
      userDao.update(user);
    } catch (Exception ex) {
      throw new ServletException(ex);
    } 
    
    resp.sendRedirect("landing");
  }
}

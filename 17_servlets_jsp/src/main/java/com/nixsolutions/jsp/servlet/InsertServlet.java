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

@WebServlet("/insert")
public class InsertServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = -3891545895807969257L;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String userName = Utils.getCookies(req, resp).get("user");

    if (userName == null) {
      return;
    }
    
    User user = new User();
    user.setLogin(req.getParameter("login"));
    user.setPassword(req.getParameter("password"));
    user.setRoleId(Integer.valueOf(req.getParameter("roles")));
    
    try {
      DAOFactory fact = DAOFactory.getFactory();
      UserDAO userDao = fact.getUserDAO();
      
      userDao.create(user);
    } catch (Exception ex) {
      throw new ServletException(ex);
    } 
    
    resp.sendRedirect("landing");
  } 
}

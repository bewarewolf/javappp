package com.nixsolutions.jsp.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.UserDAO;
import com.nixsolutions.jsp.servlet.utils.Utils;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = 6345997247096517415L;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> cookies = Utils.getCookies(req, resp);

    if (cookies == null) {
      return;
    }
    
    final String role = cookies.get("role");

    if (!"Admin".equals(role)) {
      return;
    }
    
    Integer userId = Integer.valueOf(req.getParameter("userid"));

    try {
      DAOFactory fact = DAOFactory.getFactory();
      UserDAO userDao = fact.getUserDAO();

      userDao.delete(userId);
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

    resp.sendRedirect("landing");
  }
}

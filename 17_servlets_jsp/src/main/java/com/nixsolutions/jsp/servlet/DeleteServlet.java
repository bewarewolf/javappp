package com.nixsolutions.jsp.servlet;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.PersonDAO;
import com.nixsolutions.jdbc.dao.UserDAO;
import com.nixsolutions.jsp.servlet.utils.Utils;

@WebServlet("/admin/deletePerson")
public class DeleteServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = 6345997247096517415L;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
    Integer userId = Integer.valueOf(req.getParameter("userId"));

    URL referer = new URL(req.getHeader("referer"));
    String ref = referer.getPath();
   
    try {
      DAOFactory fact = DAOFactory.getFactory();
      PersonDAO personDao = fact.getPersonDAO();

      personDao.delete(userId);
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
    
    
    //req.getRequestDispatcher(ref.substring(ref.lastIndexOf('/')) + '?' + referer.getQuery()).forward(req, resp);
    resp.sendRedirect(referer.toString());
  }
}

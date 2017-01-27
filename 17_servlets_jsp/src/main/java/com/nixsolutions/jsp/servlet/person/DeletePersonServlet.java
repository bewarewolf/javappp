package com.nixsolutions.jsp.servlet.person;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.jdbc.dao.DAOFactory;

@WebServlet("/admin/deletePerson")
public class DeletePersonServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = 6345997247096517415L;
  
  private static final Logger LOG = LogManager.getLogger();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {    
    Integer id = Integer.valueOf(req.getParameter("userId"));
    
    try {
      DAOFactory.getFactory().getPersonDAO().delete(id);
      resp.sendRedirect(req.getContextPath() + "/admin/persons");
    } catch (Exception ex) {
      LOG.error(ex);
      throw new ServletException(ex);
    }
  }
}

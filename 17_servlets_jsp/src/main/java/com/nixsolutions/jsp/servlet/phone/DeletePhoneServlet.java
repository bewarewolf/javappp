package com.nixsolutions.jsp.servlet.phone;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.jdbc.dao.DAOFactory;

@WebServlet("/admin/deletePhone")
public class DeletePhoneServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = 6104101867893474136L;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Integer phoneId = Integer.valueOf(req.getParameter("phoneId"));
    Integer persId = Integer.valueOf(req.getParameter("userId"));
    
    try {
      DAOFactory.getFactory().getPhoneNumberDAO().delete(phoneId);
      
      resp.sendRedirect(req.getContextPath() + "/admin/editPerson?userId=" + persId);
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
  }
}

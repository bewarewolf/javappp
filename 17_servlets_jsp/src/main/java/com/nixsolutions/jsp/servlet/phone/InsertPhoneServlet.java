package com.nixsolutions.jsp.servlet.phone;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.jdbc.bean.PhoneNumber;
import com.nixsolutions.jdbc.dao.DAOFactory;

@WebServlet("/admin/insertPhone")
public class InsertPhoneServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = -5121381100106708078L;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Integer persId = Integer.valueOf(req.getParameter("userId"));
    String phone = req.getParameter("number");
    
    try {
      DAOFactory.getFactory().getPhoneNumberDAO().create(new PhoneNumber(persId, phone));
      resp.sendRedirect(req.getContextPath() + "/admin/editPerson?userId=" + persId);
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
  }
}

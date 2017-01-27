package com.nixsolutions.jsp.servlet.semester;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.jdbc.dao.DAOFactory;

@WebServlet("/admin/deleteSemester")
public class DeleteSemesterServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = 5385173150902938136L;

  private static final Logger LOG = LogManager.getLogger();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Integer id = Integer.valueOf(req.getParameter("semesterId"));

    try {
      DAOFactory.getFactory().getSemesterDAO().delete(id);
      resp.sendRedirect(req.getContextPath() + "/semesters");
    } catch (Exception ex) {
      LOG.error(ex);
      throw new ServletException(ex);
    }
  }
}

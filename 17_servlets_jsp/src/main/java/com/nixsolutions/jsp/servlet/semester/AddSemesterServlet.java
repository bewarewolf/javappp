package com.nixsolutions.jsp.servlet.semester;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/admin/addSemester")
public class AddSemesterServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = -8430205951106493810L;

  private static final Logger LOG = LogManager.getLogger();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/jsp/semester/add_semester.jsp").forward(req, resp);
  }
}

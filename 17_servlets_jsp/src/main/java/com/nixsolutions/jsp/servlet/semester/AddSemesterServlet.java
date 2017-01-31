package com.nixsolutions.jsp.servlet.semester;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/addSemester")
public class AddSemesterServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = -8430205951106493810L;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/jsp/semester/add_semester.jsp").forward(req, resp);
  }
}

package com.nixsolutions.jdbc.dao.impl.h2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/error")
public class ErrorHandlerServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = -1669792282834142137L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Exception ex = (Exception) req.getAttribute("javax.servlet.error.exception");
    req.setAttribute("ex", ex);
    req.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
  }
}

package com.nixsolutions.jsp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = -9214578703065594751L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html");

    HttpSession ses = req.getSession(false);
    
    if (ses != null) {
      ses.invalidate();
    }
    
    resp.sendRedirect("index.html");
  }
}

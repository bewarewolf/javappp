package com.nixsolutions.jsp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = -9214578703065594751L;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html");
    Cookie loginCookie = null;
    Cookie roleCookie = null;
    Cookie[] cookies = req.getCookies();

    if (cookies != null) {
      for (Cookie cookie : cookies) {
	if (cookie.getName().equals("user")) {
	  loginCookie = cookie;
	}
	if (cookie.getName().equals("role")) {
	  roleCookie = cookie;
	}
      }
    }
    
    if (loginCookie != null) {
      loginCookie.setMaxAge(0);
      resp.addCookie(loginCookie);
    }
    
    if (roleCookie != null) {
      roleCookie.setMaxAge(0);
      resp.addCookie(roleCookie);
    }
    
    resp.sendRedirect("index.html");
  }
}

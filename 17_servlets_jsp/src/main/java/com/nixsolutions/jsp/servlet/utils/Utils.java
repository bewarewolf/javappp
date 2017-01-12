package com.nixsolutions.jsp.servlet.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.jdbc.bean.Role;
import com.nixsolutions.jdbc.bean.User;
import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.RoleDAO;

public class Utils {

  private static String getCookie(HttpServletRequest req, String name) 
      throws IOException {
    Cookie[] cookies = req.getCookies();

    if (cookies != null) {
      for (Cookie c : cookies) {
	if (c.getName().equals(name)) {
	  c.setMaxAge(10 * 60);
	  return c.getValue();
	}
      }
    }
    
    return null;
  }
  
  public static Map<String, String> getCookies(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Map<String, String> out = new HashMap<>();
    
    out.put("user", getCookie(req, "user"));
    out.put("role", getCookie(req, "role"));
    
    if (out.get("role") == null || out.get("role") == null) {
      resp.sendRedirect("index.html");
      return null;
    } 
    
    return out;
  }
  
  public static String formatHeadHtml(String title) {
    StringBuilder sb = new StringBuilder("<!DOCTYPE html>");
    
    sb.append("<http><head><title>").append(title).append("</title>");
    sb.append("<link rel=\"stylesheet\" href=\"css/style.css\">");
    sb.append("</head><body>");
    
    return sb.toString();
  }
  
  public static String formatUserHtml(User user) throws Exception {
    DAOFactory fact = DAOFactory.getFactory();
    RoleDAO roleDao = fact.getRoleDAO();

    Role role = roleDao.getById(user.getRoleId());

    String out = "<tr><td>" + user.getLogin() + "</td><td>" + user.getPassword() + "</td><td>" + role.getRoleName()
	+ "</td><td><div id=\"outer\">" + formatUpdateActionHtml(user.getId()) 
	+ formatDeleteActionHtml(user.getId())
	+ "</div></td></tr>";

    return out;
  }

  public static String formatUpdateActionHtml(Integer userId) {
    StringBuilder update = new StringBuilder();

    update.append("<div class=\"inner\">");
    update.append("<form action=\"editUser\" method=\"post\">");
    update.append("<input type=\"hidden\" name=\"userid\" value=\"" + userId + "\">");
    update.append("<input type=\"submit\" value=\"Edit\" />");
    update.append("</form></div><br />");

    return update.toString();
  }

  public static String formatDeleteActionHtml(Integer userId) {
    StringBuilder delete = new StringBuilder();

    delete.append("<div class=\"inner\">");
    delete.append("<form action=\"delete\" method=\"post\">");
    delete.append("<input type=\"hidden\" name=\"userid\" value=\"" + userId + "\">");
    delete.append("<input type=\"submit\" value=\"Delete\" />");
    delete.append("</form></div><br />");

    return delete.toString();
  }
}

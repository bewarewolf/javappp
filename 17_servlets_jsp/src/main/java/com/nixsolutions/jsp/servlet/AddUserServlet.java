package com.nixsolutions.jsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.jdbc.bean.Role;
import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.RoleDAO;
import com.nixsolutions.jsp.servlet.utils.Utils;

@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = -8157714273286688657L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doPost(req, resp);
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String userName = Utils.getCookies(req, resp).get("user");

    if (userName == null) {
      return;
    }
    
    PrintWriter pw = resp.getWriter();

    pw.write(Utils.formatHeadHtml("Add user"));
    
    pw.write("<form action=\"insert\" method=\"post\">");
    pw.write("Login: <input type=\"text\" name=\"login\" /><br />");
    pw.write("Password: <input type=\"text\" name=\"password\" /><br />");
    
    try {
      DAOFactory fact = DAOFactory.getFactory();
      RoleDAO roleDao = fact.getRoleDAO();
      
      pw.write("Role: <select name=\"roles\">");
      
      List<Role> roleList = roleDao.getAll();
      for (Role r : roleList) {
	pw.write("<option value=\"" + r.getId() + "\">" + r.getRoleName() + "</option>");
      }
      
      pw.write("</select><br />");
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
    
    pw.write("<input type=\"submit\" value=\"Create\" />");
    
    pw.write("</form>");
    pw.write("</body></html>");
  }
}

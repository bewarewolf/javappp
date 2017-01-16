package com.nixsolutions.jsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.jdbc.bean.Role;
import com.nixsolutions.jdbc.bean.User;
import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.RoleDAO;
import com.nixsolutions.jdbc.dao.UserDAO;
import com.nixsolutions.jsp.servlet.utils.Utils;

@WebServlet("/editUser")
public class EditUserServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = -871764955992719715L;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> cookies = Utils.getCookies(req, resp);

    if (cookies == null) {
      return;
    }
    
    final String userName = cookies.get("user");
    final String role = cookies.get("role");

    if (!"Admin".equals(role)) {
      return;
    }
    
    PrintWriter pw = resp.getWriter();

    pw.write(Utils.formatHeadHtml("Edit user"));
    pw.write(Utils.formatBodyHeaderHtml(userName));
    
    try {
      DAOFactory fact = DAOFactory.getFactory();
      UserDAO userDao = fact.getUserDAO();
      RoleDAO roleDao = fact.getRoleDAO();
      
      User user = userDao.getById(Integer.valueOf(req.getParameter("userid"))); 
      
      pw.write("<form action=\"update\" method=\"post\">");
      pw.write("Login: <input type=\"text\" name=\"login\" value=\"" + user.getLogin() + "\"><br />");
      pw.write("Password: <input type=\"text\" name=\"password\" value=\"" + user.getPassword() + "\"><br />");
      
      pw.write("Role: <select name=\"roles\">");
      
      List<Role> roleList = roleDao.getAll();
      for (Role r : roleList) {
	pw.write("<option value=\"" + r.getId() + "\" "
	    + (user.getRoleId().equals(r.getId()) ? "selected" : "") + ">" + r.getRoleName() + "</option>");
      }
      
      pw.write("</select><br />");
      
      pw.write("<input type=\"hidden\" name=\"userid\" value=\"" + user.getId() + "\">");
      pw.write("<input type=\"submit\" value=\"Update\" />");    
      pw.write("</form>");
      
      pw.write("<form action=\"landing\" method=\"post\">");
      pw.write("<input type=\"submit\" value=\"Cancel\" />");
      pw.write("</form>");      
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
    
    
    pw.write("</body></html>");
  }
}

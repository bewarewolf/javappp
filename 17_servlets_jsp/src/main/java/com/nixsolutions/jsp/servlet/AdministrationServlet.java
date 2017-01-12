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
import com.nixsolutions.jdbc.bean.User;
import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.RoleDAO;
import com.nixsolutions.jdbc.dao.UserDAO;
import com.nixsolutions.jsp.servlet.utils.Utils;

@WebServlet("/administration")
public class AdministrationServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = 2711327866847897937L;

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

    pw.write(Utils.formatHeadHtml("Users"));
    
    try {
      DAOFactory fact = DAOFactory.getFactory();
      UserDAO userDao = fact.getUserDAO();

      List<User> userList = userDao.getAll();

      if (userList.size() > 0) {
	userList.removeIf(u -> u.getLogin().equals(userName));

	pw.write("<table style=\"width:100%\">");
	pw.write("<tr><th>Login</th><th>Password</th><th>Role</th><th>Actions</th></tr>");

	for (User u : userList) {
	  pw.write(formatUserHtml(u));
	}

	pw.write("</table><br /><br />");
      }      
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
    
    pw.write("<form action=\"addUser\" method=\"post\">");
    pw.write("<input type=\"submit\" value=\"Add user\" />");
    pw.write("</form><br />");
    
    pw.write("</body></html>");
  }

  private String formatUserHtml(User user) throws Exception {
    DAOFactory fact = DAOFactory.getFactory();
    RoleDAO roleDao = fact.getRoleDAO();

    Role role = roleDao.getById(user.getRoleId());

    String out = "<tr><td>" + user.getLogin() + "</td><td>" + user.getPassword() + "</td><td>" + role.getRoleName()
	+ "</td><td><div id=\"outer\">" + formatUpdateActionHtml(user.getId()) 
	+ formatDeleteActionHtml(user.getId())
	+ "</div></td></tr>";

    return out;
  }

  private String formatUpdateActionHtml(Integer userId) {
    StringBuilder update = new StringBuilder();

    update.append("<div class=\"inner\">");
    update.append("<form action=\"editUser\" method=\"post\">");
    update.append("<input type=\"hidden\" name=\"userid\" value=\"" + userId + "\">");
    update.append("<input type=\"submit\" value=\"Edit\" />");
    update.append("</form></div><br />");

    return update.toString();
  }

  private String formatDeleteActionHtml(Integer userId) {
    StringBuilder delete = new StringBuilder();

    delete.append("<div class=\"inner\">");
    delete.append("<form action=\"delete\" method=\"post\">");
    delete.append("<input type=\"hidden\" name=\"userid\" value=\"" + userId + "\">");
    delete.append("<input type=\"submit\" value=\"Delete\" />");
    delete.append("</form></div><br />");

    return delete.toString();
  }
}

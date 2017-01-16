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

import com.nixsolutions.jdbc.bean.User;
import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.UserDAO;
import com.nixsolutions.jsp.servlet.utils.Utils;

@WebServlet("/landing")
public class LandingServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = 6672177343012691266L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> cookies = Utils.getCookies(req, resp);
    
    if (cookies == null) {
      return;
    }
    
    final String userName = cookies.get("user");
    final String role = cookies.get("role");

    PrintWriter pw = resp.getWriter();

    pw.write(Utils.formatHeadHtml("Welcome"));
    pw.write(Utils.formatBodyHeaderHtml(userName));

    if ("Admin".equals(role)) {
      try {
	DAOFactory fact = DAOFactory.getFactory();
	UserDAO userDao = fact.getUserDAO();

	List<User> userList = userDao.getAll();

	if (userList.size() > 0) {
	  userList.removeIf(u -> u.getLogin().equals(userName));

	  pw.write("<table style=\"width:100%\">");
	  pw.write("<tr><th>Login</th><th>Password</th><th>Role</th><th>Actions</th></tr>");

	  for (User u : userList) {
	    pw.write(Utils.formatUserHtml(u));
	  }

	  pw.write("</table><br /><br />");
	}
      } catch (Exception ex) {
	throw new ServletException(ex);
      }

      pw.write("<form action=\"addUser\" method=\"post\">");
      pw.write("<input type=\"submit\" value=\"Add user\" />");
      pw.write("</form><br />");
    }

    pw.write("</body></html>");
  }  
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
}

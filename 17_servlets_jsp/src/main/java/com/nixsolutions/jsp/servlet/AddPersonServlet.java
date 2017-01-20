package com.nixsolutions.jsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.jdbc.bean.PersonStatus;
import com.nixsolutions.jdbc.bean.PersonType;
import com.nixsolutions.jdbc.bean.Role;
import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.PersonStatusDAO;
import com.nixsolutions.jdbc.dao.PersonTypeDAO;
import com.nixsolutions.jdbc.dao.RoleDAO;
import com.nixsolutions.jsp.servlet.utils.DAOUtils;
import com.nixsolutions.jsp.servlet.utils.Utils;

@WebServlet("/admin/addPerson")
public class AddPersonServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = -8157714273286688657L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String entity = req.getParameter("entity");
    try {      
      PersonType pt = DAOUtils.getPersonTypeByValue(entity);
      req.setAttribute("type", pt.getValue());
            
      PersonStatus ps = DAOUtils.getPersonStatusByValue("Active");
      req.setAttribute("status", ps.getValue());
      
      req.setAttribute("entity", entity);
      
      req.getRequestDispatcher("/WEB-INF/jsp/admin/add_person.jsp").forward(req, resp);
    } catch (Exception ex) {
      throw new ServletException(ex);
    }    
  }
}

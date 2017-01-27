package com.nixsolutions.jsp.servlet.semester;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.SemesterDAO;

@WebServlet("/semesters")
public class SemesterListServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = -7073463400273861379L;

  private static final Logger LOG = LogManager.getLogger();
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {    
    try {
      DAOFactory fact = DAOFactory.getFactory();
      SemesterDAO semDao = fact.getSemesterDAO();
      
      req.setAttribute("semesters", semDao.getAll());
      
      req.getRequestDispatcher("/WEB-INF/jsp/semester/semester_list.jsp").forward(req, resp);
    } catch (Exception ex) {
      LOG.error(ex);
      throw new ServletException(ex);
    }
    
  }
}

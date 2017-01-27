package com.nixsolutions.jsp.servlet.semester;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.util.StringUtils;

import com.nixsolutions.jdbc.bean.Semester;
import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.SemesterDAO;

@WebServlet("/admin/processSemester")
public class InsertSemesterServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = -719694844914432377L;

  private static final Logger LOG = LogManager.getLogger();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Semester sem = new Semester();

    String id = req.getParameter("semesterId");
    if (!StringUtils.isNullOrEmpty(id)) {
      sem.setId(Integer.valueOf(id));
    }
    
    sem.setSemesterName(req.getParameter("semesterName"));
    sem.setStartDate(LocalDate.parse(req.getParameter("startDate")));
    sem.setEndDate(LocalDate.parse(req.getParameter("endDate")));
    
    try {
      SemesterDAO semesterDao = DAOFactory.getFactory().getSemesterDAO();

      if (sem.getId() != null) {
	semesterDao.update(sem);
      } else {
	semesterDao.create(sem);
      }

      resp.sendRedirect(req.getContextPath() + "/semesters");
    } catch (Exception ex) {
      LOG.error(ex);
      throw new ServletException(ex);
    }
  }
}

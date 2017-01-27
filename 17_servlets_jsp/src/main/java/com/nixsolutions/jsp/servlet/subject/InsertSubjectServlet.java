package com.nixsolutions.jsp.servlet.subject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.util.StringUtils;

import com.nixsolutions.jdbc.bean.Subject;
import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.SubjectDAO;

@WebServlet("/admin/processSubject")
public class InsertSubjectServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = -2121132070201463505L;

  private static final Logger LOG = LogManager.getLogger();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Subject subj = new Subject();

    String id = req.getParameter("subjectId");
    if (!StringUtils.isNullOrEmpty(id)) {
      subj.setId(Integer.valueOf(id));
    }
    
    subj.setSubjectName(req.getParameter("subjName"));
    subj.setSemesterId(Integer.valueOf(req.getParameter("semester")));
    subj.setTeacherId(Integer.valueOf(req.getParameter("teacher")));
    
    try {
      DAOFactory fact = DAOFactory.getFactory();
      SubjectDAO subjectDao = fact.getSubjectDAO();

      if (subj.getId() != null) {
	subjectDao.update(subj);
      } else {
	subjectDao.create(subj);
      }

      resp.sendRedirect(req.getContextPath() + "/subjects");
    } catch (Exception ex) {
      LOG.error(ex);
      throw new ServletException(ex);
    }
  }
}

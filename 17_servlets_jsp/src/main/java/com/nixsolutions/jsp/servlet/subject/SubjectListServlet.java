package com.nixsolutions.jsp.servlet.subject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.jdbc.bean.Person;
import com.nixsolutions.jdbc.bean.Semester;
import com.nixsolutions.jdbc.bean.Subject;
import com.nixsolutions.jdbc.bean.dto.SubjectDTO;
import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.PersonDAO;
import com.nixsolutions.jdbc.dao.SemesterDAO;
import com.nixsolutions.jdbc.dao.SubjectDAO;

@WebServlet("/subjects")
public class SubjectListServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = -6191008583827600365L;

  private static final Logger LOG = LogManager.getLogger();
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      DAOFactory fact = DAOFactory.getFactory();
      SubjectDAO subjDao = fact.getSubjectDAO();
      SemesterDAO semDao = fact.getSemesterDAO();
      PersonDAO persDao = fact.getPersonDAO();
      
      List<Subject> subjList = subjDao.getAll();
      
      List<SubjectDTO> result = new ArrayList<>();
      
      for (Subject subj : subjList) {
	Person p = persDao.getById(subj.getTeacherId());
	Semester s = semDao.getById(subj.getSemesterId());
	
	result.add(new SubjectDTO(subj.getId(), 
	    subj.getSubjectName(), 
	    p.getFirstName() + " " + p.getLastName(), 
	    s.getSemesterName()));
      }

      req.setAttribute("subjects", result);
      req.getRequestDispatcher("/WEB-INF/jsp/subject/subject_list.jsp").forward(req, resp);
    } catch (Exception ex) {
      LOG.error(ex);
      throw new ServletException(ex);
    }
  }
}

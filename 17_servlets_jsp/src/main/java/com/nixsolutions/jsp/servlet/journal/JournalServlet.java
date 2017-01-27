package com.nixsolutions.jsp.servlet.journal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.util.StringUtils;

import com.nixsolutions.jdbc.bean.Journal;
import com.nixsolutions.jdbc.bean.dto.JournalDTO;
import com.nixsolutions.jdbc.dao.DAOFactory;

@WebServlet("/journal")
public class JournalServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = -1765970977440302930L;

  private static final Logger LOG = LogManager.getLogger();
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String subj = req.getParameter("subject");
    String stud = req.getParameter("student");
    
    try {
      List<Journal> listRec = DAOFactory.getFactory().getJournalDAO().getAll();
      
      List<JournalDTO> listDto = new ArrayList<>();
      Set<String> subjList = new HashSet<>();
      Set<String> studList = new HashSet<>();
      
      subjList.add("All");
      studList.add("All");
      
      for (Journal rec : listRec) {
	JournalDTO r = new JournalDTO(rec);
	listDto.add(r);
	subjList.add(r.getSubject());
	studList.add(r.getStudent());
      }
      
      if (!StringUtils.isNullOrEmpty(stud) && !"All".equals(stud)) {
	listDto.removeIf(j -> !j.getStudent().equals(stud));
	req.setAttribute("selectedStud", stud);
      }
      if (!StringUtils.isNullOrEmpty(subj) && !"All".equals(subj)) {
	listDto.removeIf(j -> !j.getSubject().equals(subj));
	req.setAttribute("selectedSubj", subj);
      }
      
      req.setAttribute("journal", listDto);
      req.setAttribute("subjects", subjList);
      req.setAttribute("students", studList);
      
      req.getRequestDispatcher("/WEB-INF/jsp/journal/journal.jsp").forward(req, resp);
    } catch (Exception ex) {
      LOG.error(ex);
    } 
  }
}

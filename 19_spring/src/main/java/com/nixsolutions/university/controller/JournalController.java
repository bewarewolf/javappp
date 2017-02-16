package com.nixsolutions.university.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nixsolutions.university.dto.PersonDTO;
import com.nixsolutions.university.service.JournalService;
import com.nixsolutions.university.service.PersonService;

@Controller
@RequestMapping("/journal")
public class JournalController {

  @Autowired
  JournalService journalService;

  @Autowired
  PersonService personService;

  public static final Logger LOG = LogManager.getLogger();
  
  @RequestMapping(method = RequestMethod.GET)
  protected ModelAndView list(@RequestParam(value = "subject", required = false) String subject,
      @RequestParam(value = "student", required = false) String student) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String name = auth.getName();
    PersonDTO pers = personService.getByLogin(name);
    
    if (pers.getPersonType().equals("Student")) {
      student = pers.getName();
    }
    
    ModelAndView mav = new ModelAndView("journal/journal");
    mav.addObject("journal", journalService.getAll(subject, student));
    MyMap map = new MyMap();
    if (!StringUtils.isNullOrEmpty(subject)) {
      map.setSubject(subject);
    }
    if (!StringUtils.isNullOrEmpty(student)) {
      map.setStudent(student);
    }
    mav.addObject("map", map);
    return mav;
  }

  @ModelAttribute("subjectList")
  public List<String> getSubjects() {
    return journalService.getSubjects();
  }

  @ModelAttribute("studentList")
  public List<String> getStudents() {
    return journalService.getStudents();
  }

  public class MyMap {
    private String subject;
    private String student;

    public String getSubject() {
      return subject;
    }

    public void setSubject(String subject) {
      this.subject = subject;
    }

    public String getStudent() {
      return student;
    }

    public void setStudent(String student) {
      this.student = student;
    }
  }
}

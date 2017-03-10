package com.nixsolutions.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nixsolutions.university.dto.SubjectDTO;
import com.nixsolutions.university.service.SubjectService;

@Controller
@RequestMapping("/subjects")
public class SubjectController {

  @Autowired
  SubjectService subjectService;
  
  @RequestMapping(method = RequestMethod.GET)
  protected ModelAndView list() {
    ModelAndView mav = new ModelAndView("subject/subject_list");
    mav.addObject("subjects", subjectService.getAll()); //
    return mav;
  }
  
  @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
  protected String delete(@PathVariable("id") Integer id) {
    subjectService.delete(id);
    return "redirect:/subjects";
  }
  
  @RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
  protected ModelAndView add() {
    ModelAndView mav = new ModelAndView("subject/add_subject");
    mav.addObject("subject", new SubjectDTO());
    return mav;
  }
  
  @RequestMapping(value = "/edit/{id}", method = { RequestMethod.GET, RequestMethod.POST })
  protected ModelAndView edit(@PathVariable("id") Integer id) {
    ModelAndView mav = new ModelAndView("subject/add_subject");
    mav.addObject("subject", subjectService.getyId(id));
    return mav;
  }
  
  @RequestMapping(value = "/process", method = { RequestMethod.POST })
  protected String process(@ModelAttribute("subject") SubjectDTO subject) {
    subjectService.saveOrUpdate(subject);
    return "redirect:/subjects";
  }
  
  @ModelAttribute("semesterList")
  public List<String> getSemesters() {
    return subjectService.getSemesters();
  }

  @ModelAttribute("teacherList")
  public List<String> getTeachers() {
    return subjectService.getTeachers();
  }
}

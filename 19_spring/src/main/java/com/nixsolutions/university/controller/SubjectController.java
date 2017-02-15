package com.nixsolutions.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
}

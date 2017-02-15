package com.nixsolutions.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nixsolutions.university.service.SemesterService;

@Controller
@RequestMapping("/semesters")
public class SemesterController {

  @Autowired
  SemesterService semesterService;
  
  @RequestMapping(method = RequestMethod.GET)
  protected ModelAndView list() {
    ModelAndView mav = new ModelAndView("semester/semester_list");
    mav.addObject("semesters", semesterService.getAll());
    return mav;
  }
  
  @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
  protected String delete(@PathVariable("id") Integer id) {
    semesterService.delete(id);
    return "redirect:/semesters";
  }
}

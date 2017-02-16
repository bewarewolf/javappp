package com.nixsolutions.university.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nixsolutions.university.model.Semester;
import com.nixsolutions.university.service.SemesterService;
import com.nixsolutions.university.util.LocalDateEditor;

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
  
  @RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
  protected ModelAndView add() {
    ModelAndView mav = new ModelAndView("semester/add_semester");
    mav.addObject("semester", new Semester());
    return mav;
  }
  
  @RequestMapping(value = "/edit/{id}", method = { RequestMethod.GET, RequestMethod.POST })
  protected ModelAndView edit(@PathVariable("id") Integer id) {
    ModelAndView mav = new ModelAndView("semester/add_semester");
    mav.addObject("semester", semesterService.getById(id));
    return mav;
  }
  
  @RequestMapping(value = "/process", method = RequestMethod.POST)
  protected String process(@ModelAttribute("semester") Semester semester) {
    semesterService.saveOrUpdate(semester);
    return "redirect:/semesters";
  }
  
  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(LocalDate.class, new LocalDateEditor());
  }
}

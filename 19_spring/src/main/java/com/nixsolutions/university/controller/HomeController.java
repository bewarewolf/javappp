package com.nixsolutions.university.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

  @RequestMapping(value = "/home", method = RequestMethod.GET)
  protected ModelAndView home() {
    return new ModelAndView("home");
  }
  
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  protected ModelAndView login() {
    return new ModelAndView("login");
  }
  
  @RequestMapping(value = "/403", method = RequestMethod.GET)
  protected ModelAndView deny() {
    return new ModelAndView("permission_denied");
  }
}

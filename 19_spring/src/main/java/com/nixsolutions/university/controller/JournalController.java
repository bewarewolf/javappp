package com.nixsolutions.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nixsolutions.university.service.JournalService;

@Controller
@RequestMapping("/journal")
public class JournalController {

  @Autowired
  JournalService journalService;
  
  @RequestMapping(method = RequestMethod.GET)
  protected ModelAndView list() {
    ModelAndView mav = new ModelAndView("journal/journal");
    mav.addObject("journal", journalService.getAll());
    return mav;
  }
  
  @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
  protected String delete(@PathVariable("id") Integer id) {
    journalService.delete(id);
    return "redirect:/journal";
  }
}

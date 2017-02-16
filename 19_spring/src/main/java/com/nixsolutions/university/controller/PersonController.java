package com.nixsolutions.university.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nixsolutions.university.dto.PersonDTO;
import com.nixsolutions.university.model.Person;
import com.nixsolutions.university.model.PersonStatus;
import com.nixsolutions.university.model.PersonType;
import com.nixsolutions.university.model.PhoneNumber;
import com.nixsolutions.university.service.PersonService;
import com.nixsolutions.university.util.LocalDateEditor;

@Controller
@RequestMapping("/persons")
public class PersonController {

  @Autowired
  PersonService personService;

  @RequestMapping(method = RequestMethod.GET)
  protected ModelAndView list() {
    List<PersonDTO> persList = personService.getAll();
    ModelAndView mav = new ModelAndView("person/person_list");
    mav.addObject("persons", persList);
    return mav;
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
  protected String delete(@PathVariable("id") Integer id) {
    personService.delete(id);
    return "redirect:/persons";
  }

  @RequestMapping(value = "/edit/{id}", method = { RequestMethod.GET, RequestMethod.POST })
  protected ModelAndView edit(@PathVariable("id") Integer id) {
    ModelAndView mav = new ModelAndView("person/add_person");
    mav.addObject("person", personService.getById(id));
    mav.addObject("phoneNumber", new PhoneNumber());
    return mav;
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  protected ModelAndView add() {
    ModelAndView mav = new ModelAndView("person/add_person");
    mav.addObject("person", new Person());
    mav.addObject("phoneNumber", new PhoneNumber());
    return mav;
  }

  @RequestMapping(value = "/process", method = RequestMethod.POST)
  protected String process(@ModelAttribute("person") PersonDTO person) {
    personService.saveOrUpdate(person);
    return "redirect:/persons/edit/" + person.getId();
  }

  @RequestMapping(value = "/{personId}/deletePhone/{phoneId}", method = RequestMethod.POST)
  protected String deletePhone(@PathVariable("personId") Integer personId, 
      @PathVariable("phoneId") Integer phoneId) {
    personService.deletePhoneNumber(phoneId);
    return "redirect:/persons/edit/" + personId;
  }
  
  @RequestMapping(value = "/{personId}/addPhone", method = RequestMethod.POST)
  protected String addPhone(@PathVariable("personId") Integer personId,
      @ModelAttribute("phoneNumber") PhoneNumber phone) {
    personService.addPhoneNumber(personId, phone);
    return "redirect:/persons/edit/" + personId;
  }
  
  @ModelAttribute("typeList")
  public List<PersonType> getPersonTypes() {
    return personService.getPersonTypes();
  }

  @ModelAttribute("statusList")
  public List<PersonStatus> getPersonStatuses() {
    return personService.getPersonStatuses();
  }
  
  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(LocalDate.class, new LocalDateEditor());
  }
}

package com.nixsolutions.university.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
class GlobalDefaultExceptionHandler {
  public static final String DEFAULT_ERROR_VIEW = "error";
  public static final Logger LOG = LogManager.getLogger();

  @ExceptionHandler(value = Exception.class)
  public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
    LOG.error(e);
    ModelAndView mav = new ModelAndView();
    mav.addObject("ex", e);
    mav.setViewName(DEFAULT_ERROR_VIEW);
    return mav;
  }
}
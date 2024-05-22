package com.training.demo.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TutorialNotFoundException.class)
    public ModelAndView handleTutorielNotFoundException(TutorialNotFoundException e) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        mv.setStatus(HttpStatus.NOT_FOUND);
        mv.addObject("msg", e.getMessage());
        return mv;
    }

}

package com.hjs.shopapp.presentation.exception;

import com.hjs.shopapp.presentation.basic.MemberController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = MemberController.class)
public class ExceptionController {

    @ExceptionHandler(IllegalArgumentException.class)
    public String IllegalExHandler(IllegalArgumentException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "list";
    }


}

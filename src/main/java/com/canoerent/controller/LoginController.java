package com.canoerent.controller;

import com.canoerent.model.User;
import com.canoerent.service.RentService;
import com.canoerent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {


    @Autowired
    private UserService userService;

    @Autowired
    private RentService rentService;


    @GetMapping(value = "/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

//        @PostMapping (value = "/login")
//    public ModelAndView sublogin(User user){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("/reservation");
//        modelAndView.
//                addObject("rentList",
//                        rentService.getRent()
//                );
//        return modelAndView;
//    }

    @GetMapping(value = "/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping(value = "/register")
    public ModelAndView sotreUser(User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExist = userService.findByEmail(user.getEmail());

        if (userExist != null) {
            bindingResult.rejectValue("email", "error.user", "Email is already taken");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("register");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMesssage", "New user registered successfully");
        }
        modelAndView.setViewName("register");
        return modelAndView;
    }
}

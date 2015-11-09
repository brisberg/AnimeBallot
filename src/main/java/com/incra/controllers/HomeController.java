package com.incra.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * The <i>HomeController</i> doesn't have much to do, other than to send the home index view out to the
 * browser. This view is the Ember application.
 *
 * @author Jeff Risberg
 * @since 10/30/15
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public ModelAndView index() {

        ModelAndView modelAndView = new ModelAndView("home/index");

        return modelAndView;
    }
}

package com.incra.controllers.adminControllers;

import com.incra.controllers.dto.AdminPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * The <i>AdminHomeController</i> controller generates the home screen for all admin functions.
 * While the main screens are built with Ember, the admin screens are built on the server side
 * with Spring forms.
 *
 * @author Jeffrey Risberg
 * @since 11/15/11 (updated 11/03/15)
 */
@Controller
public class AdminHomeController extends AbstractAdminController {
    protected static Logger logger = LoggerFactory.getLogger(AdminHomeController.class);

    public AdminHomeController() {
    }

    /**
     * Construct a description of the admin screen, out of the panels, then render it. The
     * code should be checking for permissions on the specific panels, but it doesn't yet.
     */
    //@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/admin/home/**")
    public ModelAndView index() {

        List<AdminPanel> adminPanelList = new ArrayList<AdminPanel>();
        AdminPanel adminPanel;

        adminPanel = new AdminPanel("Seasons", "/admin/season");
        adminPanelList.add(adminPanel);

        adminPanel = new AdminPanel("Series", "/admin/series");
        adminPanelList.add(adminPanel);

        adminPanel = new AdminPanel("Ballots", "/admin/ballot");
        adminPanelList.add(adminPanel);

        adminPanel = new AdminPanel("Users", "/admin/user");
        adminPanelList.add(adminPanel);

        ModelAndView modelAndView = new ModelAndView("admin/home/index");
        modelAndView.addObject("adminPanelList", adminPanelList);

        return modelAndView;
    }
}
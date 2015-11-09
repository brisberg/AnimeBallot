package com.incra.controllers.adminControllers;

import com.incra.models.Ballot;
import com.incra.services.BallotService;
import com.incra.services.PageFrameworkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * The <i>AdminBallotController</i> controller implements listing and manipulation of ballots
 *
 * @author Brandon Risberg
 * @since 11/4/2015
 */
@Controller
public class AdminBallotController extends AbstractAdminController {
    protected static Logger logger = LoggerFactory.getLogger(AdminBallotController.class);

    @Autowired
    private BallotService ballotService;
    @Autowired
    private PageFrameworkService pageFrameworkService;

    public AdminBallotController() {
    }

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) throws Exception {
        // TODO: change this to include time once we set up a date selector.
        dataBinder.registerCustomEditor
                (Date.class, new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), false));
    }

    @RequestMapping(value = "/admin/ballot/**")
    public String index() {
        return "redirect:/admin/ballot/list";
    }

    @RequestMapping(value = "/admin/ballot/list")
    public ModelAndView list(Object criteria) {

        List<Ballot> ballotList = ballotService.findEntityList();

        ModelAndView modelAndView = new ModelAndView("admin/ballot/list");
        modelAndView.addObject("ballotList", ballotList);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/ballot/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable int id, Model model, HttpSession session) {

        Ballot ballot = ballotService.findEntityById(id);
        if (ballot != null) {
            model.addAttribute(ballot);
            return "admin/ballot/show";
        } else {
            pageFrameworkService.setFlashMessage(session, "No Ballot with that id");
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
            return "redirect:/admin/ballot/list";
        }
    }
}
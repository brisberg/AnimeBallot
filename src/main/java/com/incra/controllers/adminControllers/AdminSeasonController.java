package com.incra.controllers.adminControllers;

import com.incra.models.Season;
import com.incra.services.PageFrameworkService;
import com.incra.services.SeasonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * The <i>AdminSeasonController</i> controller implements listing of seasons.
 *
 * @author Brandon Risberg
 * @since 10/22/2015
 */
@Controller
public class AdminSeasonController extends AbstractAdminController {
    protected static Logger logger = LoggerFactory.getLogger(AdminSeasonController.class);

    @Autowired
    private SeasonService seasonService;
    @Autowired
    private PageFrameworkService pageFrameworkService;

    public AdminSeasonController() {
    }

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) throws Exception {
        dataBinder.registerCustomEditor
                (Date.class, new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), false));
    }

    @RequestMapping(value = "/admin/season/**")
    public String index() {
        return "redirect:/admin/season/list";
    }

    @RequestMapping(value = "/admin/season/list")
    public ModelAndView list(Object criteria) {

        List<Season> seasonList = seasonService.findEntityList();

        ModelAndView modelAndView = new ModelAndView("admin/season/list");
        modelAndView.addObject("seasonList", seasonList);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/season/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable int id, Model model, HttpSession session) {

        Season season = seasonService.findEntityById(id);
        if (season != null) {
            model.addAttribute(season);
            return "admin/season/show";
        } else {
            pageFrameworkService.setFlashMessage(session, "No Season with that id");
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
            return "redirect:/admin/season/list";
        }
    }

    @RequestMapping(value = "/admin/season/create", method = RequestMethod.GET)
    public ModelAndView create() {

        Season season = new Season();

        ModelAndView modelAndView = new ModelAndView("admin/season/create");
        modelAndView.addObject("command", season);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/season/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable int id) {
        Season season = seasonService.findEntityById(id);

        ModelAndView modelAndView = new ModelAndView("admin/season/edit");
        modelAndView.addObject("command", season);

        return modelAndView;
    }

    @RequestMapping(value = "/admin/season/save", method = RequestMethod.POST)
    public String save(final @ModelAttribute("command") @Valid Season season,
                       BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {
            for (ObjectError error: result.getAllErrors() ) {
                System.out.println(error);
            }

            // If we came from season/create, set the date created field now
            if (season.getDateCreated() == null) {
                season.setDateCreated(new Date());
            }
            return "admin/season/edit";
        }

        try {
            if (season.getDateCreated() == null) season.setDateCreated(new Date());
            season.setLastUpdated(new Date());

            seasonService.save(season);
        } catch (RuntimeException re) {
            pageFrameworkService.setFlashMessage(session, re.getMessage());
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
            return "redirect:/admin/season/list";
        }

        return "redirect:/admin/season/list";
    }

    @RequestMapping(value = "/admin/season/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id, HttpSession session) {

        Season season = seasonService.findEntityById(id);
        if (season != null) {
            try {
                seasonService.delete(season);
            } catch (RuntimeException re) {
                pageFrameworkService.setFlashMessage(session, re.getMessage());
                pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
                return "redirect:/admin/season/show/" + id;
            }
        } else {
            pageFrameworkService.setFlashMessage(session, "No Season with that id");
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
        }

        return "redirect:/admin/season/list";
    }
}
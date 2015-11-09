package com.incra.controllers.adminControllers;

import com.incra.models.Episode;
import com.incra.models.Season;
import com.incra.models.Series;
import com.incra.models.propertyEditor.SeasonPropertyEditor;
import com.incra.services.EpisodeService;
import com.incra.services.PageFrameworkService;
import com.incra.services.SeasonService;
import com.incra.services.SeriesService;
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
 * The <i>AdminEpisodeController</i> controller implements listing and manipulation of specific episodes of a series.
 *
 * @author Brandon Risberg
 * @since 11/4/2015
 */
@Controller
public class AdminEpisodeController extends AbstractAdminController {
    protected static Logger logger = LoggerFactory.getLogger(AdminEpisodeController.class);

    @Autowired
    private EpisodeService episodeService;
    @Autowired
    private SeasonService seasonService;
    @Autowired
    private PageFrameworkService pageFrameworkService;

    public AdminEpisodeController() {
    }

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) throws Exception {
        // TODO: change this to include time once we set up a date selector.
        dataBinder.registerCustomEditor
                (Date.class, new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), false));
    }

    @RequestMapping(value = "/admin/episode/**")
    public String index() {
        return "redirect:/admin/episode/list";
    }

    @RequestMapping(value = "/admin/episode/list")
    public ModelAndView list(Object criteria) {

        List<Episode> episodeList = episodeService.findEntityList();

        ModelAndView modelAndView = new ModelAndView("admin/episode/list");
        modelAndView.addObject("episodeList", episodeList);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/episode/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable int id, Model model, HttpSession session) {

        Episode episode = episodeService.findEntityById(id);
        if (episode != null) {
            model.addAttribute(episode);
            return "admin/episode/show";
        } else {
            pageFrameworkService.setFlashMessage(session, "No Episode with that id");
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
            return "redirect:/admin/episode/list";
        }
    }

    @RequestMapping(value = "/admin/episode/create", method = RequestMethod.GET)
    public ModelAndView create() {

        Episode episode = new Episode();

        ModelAndView modelAndView = new ModelAndView("admin/episode/create");
        modelAndView.addObject("command", episode);
        modelAndView.addObject("episodeList", episodeService.findEntityList());
        return modelAndView;
    }

    @RequestMapping(value = "/admin/episode/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable int id) {
        Episode episode = episodeService.findEntityById(id);

        ModelAndView modelAndView = new ModelAndView("admin/episode/edit");
        modelAndView.addObject("command", episode);
        modelAndView.addObject("seasonList", episodeService.findEntityList());

        return modelAndView;
    }

    @RequestMapping(value = "/admin/episode/save", method = RequestMethod.POST)
    public String save(final @ModelAttribute("command") @Valid Episode episode,
                       BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println(error);
            }

            // If we came from episode/create, set the date created field now
            if (episode.getDateCreated() == null) {
                episode.setDateCreated(new Date());
            }

            // TODO: work out the model
            model.addAttribute("seasonList", seasonService.findEntityList());
            return "admin/episode/edit";
        }

        try {
            if (episode.getDateCreated() == null) episode.setDateCreated(new Date());
            episode.setLastUpdated(new Date());

            episodeService.save(episode);
        } catch (RuntimeException re) {
            pageFrameworkService.setFlashMessage(session, re.getMessage());
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
            return "redirect:/admin/episode/list";
        }

        return "redirect:/admin/episode/list";
    }

    @RequestMapping(value = "/admin/episode/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id, HttpSession session) {

        Episode episode = episodeService.findEntityById(id);
        if (episode != null) {
            try {
                episodeService.delete(episode);
            } catch (RuntimeException re) {
                pageFrameworkService.setFlashMessage(session, re.getMessage());
                pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
                return "redirect:/admin/episode/show/" + id;
            }
        } else {
            pageFrameworkService.setFlashMessage(session, "No Episode with that id");
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
        }

        return "redirect:/admin/episode/list";
    }
}
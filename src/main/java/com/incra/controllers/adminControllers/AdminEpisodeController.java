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
    private SeriesService seriesService;
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

    @RequestMapping(value = "/admin/series/{seriesId}/episode/**")
    public String index(@PathVariable int seriesId) {
        return "redirect:/admin/series/" + seriesId + "/episode/list";
    }

    @RequestMapping(value = "/admin/series/{seriesId}/episode/list")
    public ModelAndView list(@PathVariable int seriesId, Object criteria) {

        Series series = seriesService.findEntityById(seriesId); // TODO: might take this out since we can infer the seriesId on the other side from the episodes.
        List<Episode> episodeList = episodeService.findEntityListBySeriesId(seriesId);

        ModelAndView modelAndView = new ModelAndView("admin/series/" + seriesId + "/episode/list");
        modelAndView.addObject("episodeList", episodeList);
        modelAndView.addObject("series", series);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/series/{seriesId}/episode/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable int seriesId, @PathVariable int id, Model model, HttpSession session) {

        Episode episode = episodeService.findEntityById(id);
        Series series = seriesService.findEntityById(seriesId);

        if (episode != null) {
            model.addAttribute(episode);
            model.addAttribute(series);
            return "admin/series/" + seriesId + "/episode/show";
        } else {
            pageFrameworkService.setFlashMessage(session, "No Episode with that id");
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
            return "redirect:/admin/series/" + seriesId + "/episode/list";
        }
    }

    @RequestMapping(value = "/admin/series/{seriesId}/episode/create", method = RequestMethod.GET)
    public ModelAndView create(@PathVariable int seriesId) {

        Series series = seriesService.findEntityById(seriesId);
        Episode episode = new Episode();
        episode.setSeries(series);

        ModelAndView modelAndView = new ModelAndView("admin/series/" + seriesId + "/episode/create");
        modelAndView.addObject("command", episode);
        modelAndView.addObject("series", series);
        modelAndView.addObject("episodeList", episodeService.findEntityList());
        return modelAndView;
    }

    @RequestMapping(value = "/admin/series/{seriesId}/episode/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable int seriesId, @PathVariable int id) {
        Series series = seriesService.findEntityById(seriesId);
        Episode episode = episodeService.findEntityById(id);

        ModelAndView modelAndView = new ModelAndView("admin/series/" + seriesId + "/episode/edit");
        modelAndView.addObject("command", episode);
        modelAndView.addObject("series", series);
        modelAndView.addObject("seasonList", episodeService.findEntityList());

        return modelAndView;
    }

    @RequestMapping(value = "/admin/series/{seriesId}/episode/save", method = RequestMethod.POST)
    public String save(@PathVariable int seriesId, final @ModelAttribute("command") @Valid Episode episode,
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
            return "admin/series/" + seriesId + "/episode/edit";
        }

        try {
            if (episode.getDateCreated() == null) episode.setDateCreated(new Date());
            episode.setLastUpdated(new Date());

            episodeService.save(episode);
        } catch (RuntimeException re) {
            pageFrameworkService.setFlashMessage(session, re.getMessage());
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
            return "redirect:/admin/series/" + seriesId + "/episode/list";
        }

        return "redirect:/admin/series/" + seriesId + "/episode/list";
    }

    @RequestMapping(value = "/admin/series/{seriesId}/episode/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int seriesId, @PathVariable int id, HttpSession session) {

        Episode episode = episodeService.findEntityById(id);
        if (episode != null) {
            try {
                episodeService.delete(episode);
            } catch (RuntimeException re) {
                pageFrameworkService.setFlashMessage(session, re.getMessage());
                pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
                return "redirect:/admin/series/" + seriesId + "/episode/show/" + id;
            }
        } else {
            pageFrameworkService.setFlashMessage(session, "No Episode with that id");
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
        }

        return "redirect:/admin/series/" + seriesId + "/episode/list";
    }
}
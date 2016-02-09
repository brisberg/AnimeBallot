package com.incra.controllers.adminControllers;

import com.incra.models.*;
import com.incra.models.propertyEditor.SeasonPropertyEditor;
import com.incra.pojo.FilterDisplay;
import com.incra.pojo.FilterType;
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

import javax.persistence.Query;
import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The <i>AdminSeriesController</i> controller implements listing of series and episodes.
 *
 * @author Brandon Risberg
 * @since 10/23/2015
 */
@Controller
public class AdminSeriesController extends AbstractAdminController {
    protected static Logger logger = LoggerFactory.getLogger(AdminSeriesController.class);

    @Autowired
    private EpisodeService episodeService;
    @Autowired
    private SeriesService seriesService;
    @Autowired
    private SeasonService seasonService;
    @Autowired
    private PageFrameworkService pageFrameworkService;

    public AdminSeriesController() {
    }

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) throws Exception {
        // TODO: change this to include time once we set up a date selector.
        dataBinder.registerCustomEditor
                (Date.class, new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), false));
        dataBinder.registerCustomEditor
                (Season.class, new SeasonPropertyEditor(seasonService));
    }

    @RequestMapping(value = "/admin/series/**")
    public String index() {
        return "redirect:/admin/series/list";
    }

    @RequestMapping(value = "/admin/series/list")
    public ModelAndView list(HttpServletRequest request) {

        List<Season> seasonList = seasonService.findEntityList();

        List<FilterDisplay> filterDisplays = new ArrayList<FilterDisplay>();
        FilterDisplay dfp;

        dfp = new FilterDisplay("title", "Title", FilterType.STRING, null);
        filterDisplays.add(dfp);

        dfp = new FilterDisplay("season", "Season", FilterType.SELECT, seasonList);
        filterDisplays.add(dfp);

        // Set up the criteria
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Series> criteria = cb.createQuery(Series.class);
        Root<Series> root = criteria.from(Series.class);
        Predicate[] predArrayList = this.createPredArray(cb, root, request);

        Query listQuery = buildListQuery(cb, criteria, root, predArrayList, request);

        CriteriaQuery<Long> criteriaCount = cb.createQuery(Long.class);
        Root rootCount = criteriaCount.from(Series.class);
        Predicate[] predArrayCount = this.createPredArray(cb, rootCount, request);

        Query countQuery = buildCountQuery(cb, criteriaCount, rootCount, predArrayCount);

        ModelAndView modelAndView = new ModelAndView("admin/series/list");
        modelAndView.addObject("filterDisplays", filterDisplays);
        modelAndView.addObject("seasonList", seasonList);
        modelAndView.addObject("seriesList", listQuery.getResultList());
        modelAndView.addObject("seriesCount", countQuery.getSingleResult());

        return modelAndView;
    }

    @RequestMapping(value = "/admin/series/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable int id, Model model, HttpSession session) {

        Series series = seriesService.findEntityById(id);
        if (series != null) {
            model.addAttribute(series);
            return "admin/series/show";
        } else {
            pageFrameworkService.setFlashMessage(session, "No Series with that id");
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
            return "redirect:/admin/series/list";
        }
    }

    @RequestMapping(value = "/admin/series/create", method = RequestMethod.GET)
    public ModelAndView create() {

        Series series = new Series();

        ModelAndView modelAndView = new ModelAndView("admin/series/create");
        modelAndView.addObject("command", series);
        modelAndView.addObject("seasonList", seasonService.findEntityList());
        return modelAndView;
    }

    @RequestMapping(value = "/admin/series/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable int id) {
        Series series = seriesService.findEntityById(id);

        ModelAndView modelAndView = new ModelAndView("admin/series/edit");
        modelAndView.addObject("command", series);
        modelAndView.addObject("seasonList", seasonService.findEntityList());

        return modelAndView;
    }

    @RequestMapping(value = "/admin/series/save", method = RequestMethod.POST)
    public String save(final @ModelAttribute("command") @Valid Series series,
                       BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println(error);
            }

            // If we came from series/create, set the date created field now
            if (series.getDateCreated() == null) {
                series.setDateCreated(new Date());
            }

            model.addAttribute("seasonList", seasonService.findEntityList());
            return "admin/series/edit";
        }

        try {
            if (series.getDateCreated() == null) series.setDateCreated(new Date());
            series.setLastUpdated(new Date());

            seriesService.save(series);
        } catch (RuntimeException re) {
            pageFrameworkService.setFlashMessage(session, re.getMessage());
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
            return "redirect:/admin/series/list";
        }

        return "redirect:/admin/series/list";
    }

    @RequestMapping(value = "/admin/series/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id, HttpSession session) {

        Series series = seriesService.findEntityById(id);
        if (series != null) {
            try {
                seriesService.delete(series);
            } catch (RuntimeException re) {
                pageFrameworkService.setFlashMessage(session, re.getMessage());
                pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
                return "redirect:/admin/series/show/" + id;
            }
        } else {
            pageFrameworkService.setFlashMessage(session, "No Series with that id");
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
        }

        return "redirect:/admin/series/list";
    }

    // Episode mappings

    @RequestMapping(value = "/admin/series/{seriesId}/episode/list")
    public ModelAndView listEpisodes(@PathVariable int seriesId, Object criteria) {

        Series series = seriesService.findEntityById(seriesId); // TODO: might take this out since we can infer the seriesId on the other side from the episodes.
        List<Episode> episodeList = episodeService.findEntityListBySeriesId(seriesId);

        ModelAndView modelAndView = new ModelAndView("admin/series/episode/list");
        modelAndView.addObject("episodeList", episodeList);
        modelAndView.addObject("series", series);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/series/{seriesId}/episode/show/{id}", method = RequestMethod.GET)
    public String showEpisode(@PathVariable int seriesId, @PathVariable int id, Model model, HttpSession session) {

        Episode episode = episodeService.findEntityById(id);
        Series series = seriesService.findEntityById(seriesId);

        if (episode != null) {
            model.addAttribute(episode);
            model.addAttribute(series);
            return "admin/series/episode/show";
        } else {
            pageFrameworkService.setFlashMessage(session, "No Episode with that id");
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
            return "redirect:/admin/series/" + seriesId + "/episode/list";
        }
    }

    @RequestMapping(value = "/admin/series/{seriesId}/episode/create", method = RequestMethod.GET)
    public ModelAndView createEpisode(@PathVariable int seriesId) {

        Series series = seriesService.findEntityById(seriesId);
        Episode episode = new Episode();
        episode.setSeries(series);

        ModelAndView modelAndView = new ModelAndView("admin/series/episode/create");
        modelAndView.addObject("command", episode);
        modelAndView.addObject("series", series);
        modelAndView.addObject("episodeList", episodeService.findEntityList());
        return modelAndView;
    }

    @RequestMapping(value = "/admin/series/{seriesId}/episode/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editEpisode(@PathVariable int seriesId, @PathVariable int id) {
        Series series = seriesService.findEntityById(seriesId);
        Episode episode = episodeService.findEntityById(id);

        ModelAndView modelAndView = new ModelAndView("admin/series/episode/edit");
        modelAndView.addObject("command", episode);
        modelAndView.addObject("series", series);
        modelAndView.addObject("seasonList", episodeService.findEntityList());

        return modelAndView;
    }

    @RequestMapping(value = "/admin/series/{seriesId}/episode/save", method = RequestMethod.POST)
    public String saveEpisode(@PathVariable int seriesId, final @ModelAttribute("command") @Valid Episode episode,
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
            return "admin/series/episode/edit";
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
    public String deleteEpisode(@PathVariable int seriesId, @PathVariable int id, HttpSession session) {

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

    protected Predicate[] createPredArray(CriteriaBuilder cb, Root root, HttpServletRequest request) {
        List<Predicate> predList = new ArrayList<Predicate>();
        if (request.getParameter("title") != null && request.getParameter("title").trim() != null) {
            predList.add(
                    cb.like(cb.lower(root.get("title")),
                            "%" + request.getParameter("title").trim().toLowerCase() + "%"));
        }
        if (request.getParameter("season") != null && request.getParameter("season").trim() != null) {
            try {
                Season season = seasonService.findEntityById(Integer.parseInt(request.getParameter("season")));
                if (season != null)
                    predList.add(
                            cb.equal(root.get("season"),
                                    season));
            } catch (Exception e) {
                // nothing to do here
            }
        }
        Predicate[] predArray = new Predicate[predList.size()];
        predList.toArray(predArray);
        return predArray;
    }
}
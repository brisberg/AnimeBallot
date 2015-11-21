package com.incra.controllers;

import com.incra.controllers.adminControllers.AbstractAdminController;
import com.incra.models.Episode;
import com.incra.models.Series;
import com.incra.models.User;
import com.incra.services.EpisodeService;
import com.incra.services.PageFrameworkService;
import com.incra.services.SeriesService;
import com.incra.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The <i>ApiController</i> handles all api fetches.
 *
 * @author Jeffrey Risberg
 * @since 10/25/14
 */
@Controller
public class ApiController {
    @Autowired
    private UserService userService;
    @Autowired
    private SeriesService seriesService;
    @Autowired
    private EpisodeService episodeService;
    @Autowired
    private PageFrameworkService pageFrameworkService;

    @RequestMapping(value = "/api/series", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> apiSeriesList(HttpServletRequest request, HttpSession session) {
        List<Series> seriesList = seriesService.findEntityList();

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("series", seriesList);

        return result;
    }

    @RequestMapping(value = "/api/series/{id}", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> apiSeries(@PathVariable("id") int id, HttpServletRequest request, HttpSession session) {
        Series series = seriesService.findEntityById(id);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("series", series);

        return result;
    }

    @RequestMapping(value = "/api/episodes", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> apiEpisodeList(HttpServletRequest request, HttpSession session) {

        List<Episode> episodeList = new ArrayList<Episode>();

        String weekIndexStr = request.getParameter("weekIndex");
        if (weekIndexStr != null) {
            try {
                Integer weekIndex = Integer.parseInt(weekIndexStr);
                episodeList = episodeService.findEntityListByWeekIndex(weekIndex);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            episodeList = episodeService.findEntityList();
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("episodes", episodeList);

        return result;
    }

    @RequestMapping(value = "/api/episodes/{id}", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> apiSeriesList(@PathVariable("id") int id, HttpSession session) {
        Episode episode = episodeService.findEntityById(id);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("episode", episode);

        return result;
    }

    @RequestMapping(value = "/api/users", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> apiUserList(HttpServletRequest request, HttpSession session) {
        List<User> userLists = userService.findEntityList();

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("users", userLists);

        return result;
    }
}

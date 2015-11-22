package com.incra.controllers;

import com.incra.controllers.adminControllers.AbstractAdminController;
import com.incra.models.*;
import com.incra.services.*;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The <i>ApiController</i> handles all REST API methods.
 *
 * @author Jeffrey Risberg
 * @since 10/30/14
 */
@Controller
public class ApiController {

    @Autowired
    private SeasonService seasonService;
    @Autowired
    private SeriesService seriesService;
    @Autowired
    private EpisodeService episodeService;
    @Autowired
    private BallotService ballotService;
    @Autowired
    private UserService userService;
    @Autowired
    private PageFrameworkService pageFrameworkService;

    @RequestMapping(value = "/api/seasons", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> apiSeasonList(HttpServletRequest request, HttpSession session) {
        List<Season> seasonList = seasonService.findEntityList();

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("seasons", seasonList);

        return result;
    }

    @RequestMapping(value = "/api/seasons/{id}", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> apiSeason(@PathVariable("id") int id, HttpServletRequest request, HttpSession session) {
        Season season = seasonService.findEntityById(id);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("season", season);

        return result;
    }

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
    Map<String, Object> apiEpisode(@PathVariable("id") int id, HttpSession session) {
        Episode episode = episodeService.findEntityById(id);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("episode", episode);

        return result;
    }

    @RequestMapping(value = "/api/ballots", method = RequestMethod.GET, headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> apiBallotList(HttpServletRequest request, HttpSession session) {
        System.out.println("apiBallotList");
        List<Ballot> ballotList = ballotService.findEntityList();

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("ballots", ballotList);

        return result;
    }

    @RequestMapping(value = "/api/ballots", method = RequestMethod.POST, headers = "Accept=application/json")
    public
    @ResponseBody
    Ballot apiBallotPost(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        System.out.println("apiBallotPost");
        String seasonIdStr = request.getParameter("season");
        String weekIndexStr = request.getParameter("weekIndex");
        String userIdStr = request.getParameter("user");

        System.out.println(seasonIdStr + " " + weekIndexStr);

        Ballot ballot = null;

        try {
            int seasonId = Integer.parseInt(seasonIdStr);
            int userId = Integer.parseInt(userIdStr);
            int weekIndex = Integer.parseInt(weekIndexStr);
            Season season = seasonService.findEntityById(seasonId);
            User user = userService.findEntityById(userId);

            ballot = new Ballot();
            ballot.setSeason(season);
            ballot.setUser(user);
            ballot.setWeekIndex(weekIndex);

            ballotService.save(ballot);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        response.setStatus(201);
        return ballot;
    }


    @RequestMapping(value = "/api/ballots/{id}", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> apiBallot(@PathVariable("id") int id, HttpSession session) {
        Ballot ballot = ballotService.findEntityById(id);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("ballot", ballot);

        return result;
    }

    @RequestMapping(value = "/api/users", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> apiUserList(HttpServletRequest request, HttpSession session) {
        List<User> userList = userService.findEntityList();

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("users", userList);

        return result;
    }

    @RequestMapping(value = "/api/users/{id}", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> apiUser(@PathVariable("id") int id, HttpSession session) {
        User user = userService.findEntityById(id);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("user", user);

        return result;
    }
}

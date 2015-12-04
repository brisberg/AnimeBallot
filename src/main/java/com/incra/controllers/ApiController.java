package com.incra.controllers;

import com.incra.controllers.adminControllers.AbstractAdminController;
import com.incra.models.*;
import com.incra.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
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
    private BallotVoteService ballotVoteService;
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
        List<Ballot> ballotList = ballotService.findEntityList();

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("ballots", ballotList);

        return result;
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

    @RequestMapping(value = "/api/ballots", method = RequestMethod.POST)
    public
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    Map<String, Object> apiBallotPost(@RequestBody Object data) {
        Map ballotData = (Map) ((Map) data).get("ballot");

        try {
            String comment = (String) ballotData.get("comment");
            Integer weekIndex = (Integer) ballotData.get("weekIndex");
            String seasonIdStr = (String) ballotData.get("season");
            String userIdStr = (String) ballotData.get("user");
            int seasonId = Integer.parseInt(seasonIdStr);
            int userId = Integer.parseInt(userIdStr);
            Season season = seasonService.findEntityById(seasonId);
            User user = userService.findEntityById(userId);

            Ballot ballot = new Ballot();
            ballot.setSeason(season);
            ballot.setUser(user);
            ballot.setWeekIndex(weekIndex);
            ballot.setComment(comment);

            List<Map> ballotVotesData = (List<Map>) ballotData.get("ballotVotes");

            if (ballotVotesData != null) {
                for (Map ballotVoteData : ballotVotesData) {

                    Integer score = (Integer) ballotVoteData.get("score");
                    String episodeIdStr = (String) ballotVoteData.get("episode");
                    System.out.println(score);
                    System.out.println(episodeIdStr);
                    int episodeId = Integer.parseInt(episodeIdStr);
                    Episode episode = episodeService.findEntityById(episodeId);

                    BallotVote ballotVote = new BallotVote();
                    ballotVote.setEpisode(episode);
                    ballotVote.setScore(score);

                    ballot.getBallotVotes().add(ballotVote);
                    ballotVote.setBallot(ballot);
                }
            }

            // This cascades to also save the ballot votes
            ballotService.save(ballot);

            Map<String, Object> result = new HashMap<String, Object>();
            result.put("ballot", ballot);

            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @RequestMapping(value = "/api/ballot_votes", method = RequestMethod.GET, headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> apiBallotVoteList(HttpServletRequest request, HttpSession session) {
        List<BallotVote> ballotVoteList = ballotVoteService.findEntityList();

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("ballotVotes", ballotVoteList);

        return result;
    }

    @RequestMapping(value = "/api/ballot_votes/{id}", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> apiBallotVote(@PathVariable("id") int id, HttpSession session) {
        BallotVote ballotVote = ballotVoteService.findEntityById(id);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("ballotVote", ballotVote);

        return result;
    }

    @RequestMapping(value = "/api/ballot_votes", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void apiBallotVotePost(@RequestBody Object data) {
        Map ballotVoteData = (Map) ((Map) data).get("ballot-vote");

        try {
            //Integer weekIndex = (Integer) ballotData.get("weekIndex");
            //String seasonIdStr = (String) ballotData.get("season");
            //String userIdStr = (String) ballotData.get("user");
            //int seasonId = Integer.parseInt(seasonIdStr);
            //int userId = Integer.parseInt(userIdStr);
            //Season season = seasonService.findEntityById(seasonId);
            //User user = userService.findEntityById(userId);

            BallotVote ballotVote = new BallotVote();
            //ballot.setSeason(season);
            //ballot.setUser(user);
            //ballot.setWeekIndex(weekIndex);

            ballotVoteService.save(ballotVote);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @RequestMapping(value = "/api/users", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> apiUserList(HttpServletRequest request, HttpSession session) {

        List<User> userList = new ArrayList<User>();

        String name = request.getParameter("name");
        if (name != null) {
            try {
                User user = userService.findEntityByName(name);
                if (user != null)
                    userList.add(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            userList = userService.findEntityList();
        }

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

    @RequestMapping(value = "/api/users", method = RequestMethod.POST)
    public
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    Map<String, Object> apiUserPost(@RequestBody Object data) {
        Map userData = (Map) ((Map) data).get("user");

        try {
            String name = (String) userData.get("name");

            User user = new User();
            user.setName(name);
            user.setFirstName("dummy");
            user.setLastName("dummy");
            user.setEmail("dummy@gmail.com");

            userService.save(user);

            Map<String, Object> result = new HashMap<String, Object>();
            result.put("user", user);

            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}

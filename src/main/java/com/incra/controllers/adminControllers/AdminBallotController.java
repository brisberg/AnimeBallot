package com.incra.controllers.adminControllers;

import com.incra.models.*;
import com.incra.pojo.FilterDisplay;
import com.incra.pojo.FilterType;
import com.incra.services.BallotService;
import com.incra.services.PageFrameworkService;
import com.incra.services.SeasonService;
import com.incra.services.UserService;
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

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private SeasonService seasonService;
    @Autowired
    private UserService userService;
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
    public ModelAndView list(HttpServletRequest request) {

        //List<Ballot> ballotList = ballotService.findEntityList();
        List<Season> seasonList = seasonService.findEntityList();

        List<FilterDisplay> filterDisplays = new ArrayList<FilterDisplay>();
        FilterDisplay dfp;

        dfp = new FilterDisplay("season", "Season", FilterType.SELECT, seasonList);
        filterDisplays.add(dfp);

        dfp = new FilterDisplay("user", "User", FilterType.STRING, null);
        filterDisplays.add(dfp);

        //set up the query
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Ballot> criteria = cb.createQuery(Ballot.class);
        Root<Ballot> root = criteria.from(Ballot.class);

        List<Predicate> predList = new ArrayList<Predicate>();
        if (false && request.getParameter("user") != null && request.getParameter("user").trim() != null) {
            User user = userService.findEntityByName(request.getParameter("user").trim());
            predList.add(
                    cb.equal(root.get("user"),
                            user)); // TODO: add a join to actually look up the user name
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

        Query listQuery = buildListQuery(cb, criteria, root, predArray, request);

        CriteriaQuery<Long> criteriaCount = cb.createQuery(Long.class);
        Root rootCount = criteriaCount.from(Ballot.class);

        Query countQuery = buildCountQuery(cb, criteriaCount, rootCount, predArray);

        ModelAndView modelAndView = new ModelAndView("admin/ballot/list");
        modelAndView.addObject("filterDisplays", filterDisplays);
        modelAndView.addObject("seasonList", seasonList);
        modelAndView.addObject("ballotList", listQuery.getResultList());
        modelAndView.addObject("ballotCount", countQuery.getSingleResult());
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
package com.incra.controllers.adminControllers;

import com.incra.models.Configuration;
import com.incra.models.Season;
import com.incra.models.propertyEditor.SeasonPropertyEditor;
import com.incra.services.ConfigurationService;
import com.incra.services.PageFrameworkService;
import com.incra.services.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * The <i>AdminConfigurationController</i> allows system admin to update the configuration.
 *
 * @author Brandon Risberg
 * @since 12/8/15
 */
@Controller
public class AdminConfigurationController extends AbstractAdminController {
    @Autowired
    private ConfigurationService configurationService;
    @Autowired
    private PageFrameworkService pageFrameworkService;
    @Autowired
    private SeasonService seasonService;

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) throws Exception {
        dataBinder.registerCustomEditor
                (Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"), false));
        dataBinder.registerCustomEditor
                (Season.class, new SeasonPropertyEditor(seasonService));
    }

    @RequestMapping(value = "/admin/configuration/**")
    public String index() {
        return "redirect:/admin/configuration/show";
    }

    @RequestMapping(value = "/admin/configuration/show", method = RequestMethod.GET)
    public String showConfiguration(Model model) {

        Configuration config = configurationService.findActiveEntity();

        if (config != null) {
            model.addAttribute(config);
            return "admin/configuration/show";
        } else {
            return "redirect:/admin/configuration/create";
        }
    }

    @RequestMapping(value = "/admin/configuration/edit", method = RequestMethod.GET)
    public ModelAndView edit() {

        Configuration config = configurationService.findActiveEntity();

        ModelAndView modelAndView = new ModelAndView("admin/configuration/edit");
        modelAndView.addObject("command", config);
        modelAndView.addObject("seasonList", seasonService.findEntityList());

        return modelAndView;
    }

    @RequestMapping(value = "/admin/configuration/save", method = RequestMethod.POST)
    public String saveConfiguration(@ModelAttribute("configuration") Configuration config, BindingResult result, Model model, HttpSession session) {

        configurationService.save(config);

        try {
            if (config.getDateCreated() == null) config.setDateCreated(new Date());
            config.setLastUpdated(new Date());

            configurationService.save(config);
        } catch (RuntimeException re) {
            pageFrameworkService.setFlashMessage(session, re.getMessage());
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
            return "redirect:/admin/configuration/show";
        }
        return "redirect:/admin/configuration/show";
    }
}

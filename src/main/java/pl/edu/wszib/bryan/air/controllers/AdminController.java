package pl.edu.wszib.bryan.air.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.bryan.air.model.Flight;
import pl.edu.wszib.bryan.air.model.User;
import pl.edu.wszib.bryan.air.services.IFlightService;
import pl.edu.wszib.bryan.air.services.ITicketService;
import pl.edu.wszib.bryan.air.session.SessionObject;

import javax.annotation.Resource;


@Controller
public class AdminController {

    @Autowired
    IFlightService flightService;

    @Autowired
    ITicketService ticketService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable int id, Model model) {
        if (!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/login";
        }
        Flight flight = this.flightService.getFlightById(id);
        model.addAttribute("flight", flight);
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);
        return "edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@ModelAttribute Flight flight) {
        if (!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/login";
        }
        this.flightService.updateFlight(flight);
        return "redirect:/main";
    }

    @RequestMapping(value = "/add-flight", method = RequestMethod.GET)
    public String addFlightForm(Model model) {
        if (!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/login";
        }
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("flightModel", new Flight());
        return "/add-flight";
    }

    @RequestMapping(value = "/add-flight", method = RequestMethod.POST)
    public String addFlightSubmit(@ModelAttribute Flight flight) {
        this.flightService.addFlight(flight);
        return "redirect:/main";
    }

    @RequestMapping(value = "/remove-flight/{id}", method = RequestMethod.GET)
    public String removeFlightForm(@PathVariable int id) {
        if (!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/login";
        }
        this.flightService.removeFlight(this.flightService.getFlightById(id));
        return "redirect:/main";
    }

}



package pl.edu.wszib.bryan.air.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.bryan.air.model.Flight;
import pl.edu.wszib.bryan.air.services.IBasketService;
import pl.edu.wszib.bryan.air.services.IFlightService;
import pl.edu.wszib.bryan.air.services.ITicketService;
import pl.edu.wszib.bryan.air.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class BasketController {

    @Autowired
    IFlightService flightService;

    @Autowired
    ITicketService ticketService;

    @Autowired
    IBasketService basketService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String basket(Model model) {
        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        model.addAttribute("flights", this.sessionObject.getBasket());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("sum", this.basketService.calculateTotal());
        return "basket";
    }

    @RequestMapping(value = "/add-to-basket/{id}", method = RequestMethod.GET)
    public String addToBasket(@PathVariable int id) {
        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        this.basketService.addFlightByIdToBasket(id);
        return "redirect:/main";
    }

    @RequestMapping(value = "/remove-from-basket/{id}", method = RequestMethod.GET)
    public String removeFromBasket(@PathVariable int id) {
        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        this.basketService.removeFlightByIdFromBasket(id);
        return "redirect:/main";
    }

    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public String buy() {
        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }

        List<Flight> flights = this.sessionObject.getBasket();
        for (Flight flight : flights) {
            this.ticketService.saveTicket(flight.getId());
        }
        this.sessionObject.getBasket().clear();
        //TODO Dodac komunikat na stronie
        System.out.println("Kupiłeś");
        return "redirect:/main";
    }
}







package pl.edu.wszib.bryan.air.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.bryan.air.dao.ITicketDAO;
import pl.edu.wszib.bryan.air.model.Flight;
import pl.edu.wszib.bryan.air.model.Ticket;
import pl.edu.wszib.bryan.air.services.IBasketService;
import pl.edu.wszib.bryan.air.services.IFlightService;
import pl.edu.wszib.bryan.air.services.ITicketService;
import pl.edu.wszib.bryan.air.session.SessionObject;
import javax.annotation.Resource;
import java.util.List;

@Service
public class TicketServiceImpl implements ITicketService {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IBasketService basketService;

    @Autowired
    IFlightService flightService;

    @Autowired
    ITicketDAO ticketDAO;

    @Override
    public boolean saveTicket(int id) {
        List<Flight> flights = this.flightService.getAllFlights();
        if (!this.basketService.checkIfExist(id))
            for (Flight userFlight : flights) {
                if (userFlight.getId() == id) {
                    Ticket ticket = new Ticket();
                    ticket.setStatus(Ticket.Status.ORDERED);
                    ticket.setUser(this.sessionObject.getLoggedUser());
                    ticket.setFlight(userFlight);
                    this.ticketDAO.saveTicket(ticket);
                    return true;
                }
            }
        return false;
    }

    @Override
    public Ticket getTicketById(int id) {
        return this.ticketDAO.getTicketById(id);
    }
}

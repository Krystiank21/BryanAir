package pl.edu.wszib.bryan.air.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.bryan.air.dao.IFlightDAO;
import pl.edu.wszib.bryan.air.model.Flight;
import pl.edu.wszib.bryan.air.services.IBasketService;
import pl.edu.wszib.bryan.air.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;
import java.util.ListIterator;

@Service
public class BasketServiceImpl implements IBasketService {

    @Autowired
    IFlightDAO flightDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public double calculateTotal() {
        double sum = 0;
        for (Flight flight : this.sessionObject.getBasket()) {
            sum = sum + flight.getPrice();
        }
        return sum;
    }

    @Override
    public void addFlightByIdToBasket(int id) {
        Flight flight = this.flightDAO.getFlightById(id);
        for (Flight flightFromBasket : this.sessionObject.getBasket()) {
            if (flightFromBasket.getId() == flight.getId()) {
                System.out.println("masz już bilet na ten samolot");
                //TODO Dodac komunikat na stronie
                return;
            }
        }
        flight.setSeats(flight.getSeats() - 1);
        this.flightDAO.updateFlight(flight);
        this.sessionObject.getBasket().add(flight);
    }

    @Override
    public void removeFlightByIdFromBasket(int id) {
        Flight flight = this.flightDAO.getFlightById(id);
        List<Flight> flightFromBasket = this.sessionObject.getBasket();
        ListIterator<Flight> iter = flightFromBasket.listIterator();
        while (iter.hasNext()) {
            if (iter.next().getId() == flight.getId()) {
                iter.remove();
                flight.setSeats(flight.getSeats() + 1);
                this.flightDAO.updateFlight(flight);
            }
        }
        this.sessionObject.setBasket(flightFromBasket);
    }

    @Override
    public boolean checkIfExist(int id) {
        List<Flight> userFlights = this.sessionObject.getBasket();
        if (userFlights.isEmpty()) {
            return false;
        }
        for (Flight flightFromBasket : userFlights) {
            if (flightFromBasket.getId() == id) {
                return true;
            }
        }
        return false;
    }
}


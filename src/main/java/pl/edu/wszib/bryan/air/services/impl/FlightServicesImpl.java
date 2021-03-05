package pl.edu.wszib.bryan.air.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.bryan.air.dao.IFlightDAO;
import pl.edu.wszib.bryan.air.model.Flight;
import pl.edu.wszib.bryan.air.services.IFlightService;
import java.util.List;
import java.util.ListIterator;

@Service
public class FlightServicesImpl implements IFlightService {

    @Autowired
    IFlightDAO flightDAO;

    @Override
    public Flight getFlightById(int id) {
        return this.flightDAO.getFlightById(id);
    }

    @Override
    public void updateFlight(Flight flight) {
        if (flight.getSeats() < 1) {
            //TODO Dodac komunikat na stronie
            System.out.println("Wprowadz poprawna ilosc miejsc");
            return;
        }
        Flight flightFromDB = this.flightDAO.getFlightById(flight.getId());
        flightFromDB.setFlightNumber(flight.getFlightNumber());
        flightFromDB.setArrival(flight.getArrival());
        flightFromDB.setDeparture(flight.getDeparture());
        flightFromDB.setSeats(flight.getSeats());
        flightFromDB.setPrice(flight.getPrice());

        this.flightDAO.updateFlight(flightFromDB);
    }

    @Override
    public List<Flight> getAllFlights() {
        return this.flightDAO.getAllFlights();
    }

    @Override
    public List<Flight> getAllAvailableFlights() {
        List<Flight> allFlights = getAllFlights();

        ListIterator<Flight> iter = allFlights.listIterator();
        while (iter.hasNext()) {
            if (iter.next().getSeats() < 1) {
                iter.remove();
            }
        }
        return allFlights;
    }


    @Override
    public void addFlight(Flight flight) {
        if (flight.getSeats() < 1 || flight.getPrice() < 0) {
            //TODO dodac komunikat na stronie
            System.out.println("Podaj prawidłowe wartosci");
            return;
        }
        this.flightDAO.persistFlight(flight);
    }

    @Override
    public void removeFlight(Flight flight) {
        this.flightDAO.removeFlight(flight);
    }
}

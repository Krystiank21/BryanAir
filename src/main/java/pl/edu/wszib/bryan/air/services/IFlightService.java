package pl.edu.wszib.bryan.air.services;

import pl.edu.wszib.bryan.air.model.Flight;
import java.util.List;

public interface IFlightService {
    Flight getFlightById(int id);
    void updateFlight(Flight flight);
    List<Flight> getAllFlights();
    List<Flight> getAllAvailableFlights();
    void addFlight(Flight flight);
    void removeFlight(Flight flight);

}

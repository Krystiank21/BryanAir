package pl.edu.wszib.bryan.air.dao;

import pl.edu.wszib.bryan.air.model.Flight;

import java.util.List;

public interface IFlightDAO {
    Flight getFlightById(int id);
    void updateFlight(Flight flight);
    List<Flight> getAllFlights();
    void persistFlight(Flight flight);
    void removeFlight(Flight flight);
}

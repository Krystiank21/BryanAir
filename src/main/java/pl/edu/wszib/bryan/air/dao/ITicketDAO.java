package pl.edu.wszib.bryan.air.dao;

import pl.edu.wszib.bryan.air.model.Ticket;

public interface ITicketDAO {
    void saveTicket(Ticket ticket);
    Ticket getTicketById(int id);
}

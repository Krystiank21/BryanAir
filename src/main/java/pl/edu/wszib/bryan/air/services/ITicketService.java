package pl.edu.wszib.bryan.air.services;

import pl.edu.wszib.bryan.air.model.Ticket;

public interface ITicketService {
     boolean saveTicket(int id);
     Ticket getTicketById(int id);
}

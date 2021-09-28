package com.app.Service;

import java.util.List;

import com.app.pojos.Tickets;

public interface ITicketService {
	public Tickets bookTickets(Tickets t, int busId, int customerId);
	public Tickets cancelTickets(int ticketId);
	public List<Tickets> getTicketsOfUser(int userId);
	public List<Tickets> getTicketsOfBus(int busId, String date);

}

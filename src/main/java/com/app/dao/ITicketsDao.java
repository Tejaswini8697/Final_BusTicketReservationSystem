package com.app.dao;

import java.util.List;

import com.app.pojos.Tickets;

public interface ITicketsDao {

	public Tickets bookTickets(Tickets t, int busId,int customerId);
	public Tickets cancelTickets(int ticketId);
	public List<Tickets> getTicketsOfUser(int userId);
	public List<Tickets> getTicketsOfBus(int busId, String date);
}

package com.app.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.ITicketsDao;
import com.app.pojos.Tickets;

@Service
@Transactional
public class TicketServiceImpl implements ITicketService {

	@Autowired
	private ITicketsDao dao;
	
	@Override
	public Tickets bookTickets(Tickets t, int busId, int customerId) {
		return dao.bookTickets(t, busId, customerId);
	}

	@Override
	public Tickets cancelTickets(int ticketId) {
		System.out.println("in cancel ticket");
		return dao.cancelTickets(ticketId);
	}

	@Override
	public List<Tickets> getTicketsOfUser(int userId) {
		return dao.getTicketsOfUser(userId);
	}

	@Override
	public List<Tickets> getTicketsOfBus(int busId, String date) {
		return dao.getTicketsOfBus(busId, date);
	}

}

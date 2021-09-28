package com.app.dao;


import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.Bus;
import com.app.pojos.DayFromDate;
import com.app.pojos.Tickets;
import com.app.pojos.User;

@Repository
public class TicketsDaoImpl implements ITicketsDao {
	
	@PersistenceContext
	private EntityManager mgr;
	
	@Autowired
	private ISeatsDao seatsDao;
	
	@Override
	public Tickets bookTickets(Tickets t, int busId, int customerId) {
		Bus b=mgr.find(Bus.class,busId);
		System.out.println(b);
		User u=mgr.find(User.class,customerId);
		System.out.println(u);
		b.addTickets(t);
		u.addTickets(t);
		System.out.println("addition done");
		seatsDao.bookSeatsByBus(busId, DayFromDate.getStringDate(t.getBookedDate()),t.getNoOfSeats());
		mgr.merge(b);
		mgr.merge(u);
		return t;
		
	}

	@Override
	public Tickets cancelTickets(int ticketId) {
		System.out.println("dao cancel");
		Tickets t=mgr.find(Tickets.class,ticketId);
		System.out.println(t);
		/*Bus b=mgr.find(Bus.class,t.getBusId().getId());
		System.out.println(b);
		seatsDao.addSeatsByBus(b.getId(),t.getBookedDate().toString(), t.getNoOfSeats());
		System.out.println("seats added");
		t.setNoOfSeats(0);*/
		mgr.remove(t);
		System.out.println("ticket removed");
		/*mgr.remove(b);
		System.out.println("bus removed");*/
		return null;
	}

	@Override
	public List<Tickets> getTicketsOfUser(int userId) {
		String jpql = "select t from Tickets t where t.userId = :user";
		User u = mgr.find(User.class, userId);
		List<Tickets> list = mgr.createQuery(jpql, Tickets.class).setParameter("user", u).getResultList();
		return list;
	}

	@Override
	public List<Tickets> getTicketsOfBus(int busId, String date) {
		String jpql = "select t from Tickets t where t.busId = :bus and t.bookedDate = :date";
		Bus b = mgr.find(Bus.class, busId);
		Date d = DayFromDate.getDate(date);
		List<Tickets> list = mgr.createQuery(jpql, Tickets.class).setParameter("bus", b).setParameter("date", d).getResultList();
		return list;
	}

}

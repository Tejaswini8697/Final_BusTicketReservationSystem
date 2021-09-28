package com.app.dao;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.app.pojos.Bus;
import com.app.pojos.DayFromDate;
import com.app.pojos.Seats;

@Repository
public class SeatsDaoImpl implements ISeatsDao {
	
	@PersistenceContext
	private EntityManager mgr;

	@Override
	public Seats getSeatsByBusId(int busId, String date) {
		System.out.println("in seats dao");
		String jpql="select s from Seats s where s.busId = :busId and s.bookDate = :date";
		Bus b=mgr.find(Bus.class,busId);
		System.out.println(b);
		LocalDate d=DayFromDate.getLocalDate(date);
		System.out.println(d);
		Seats s;
		try {
			s=mgr.createQuery(jpql,Seats.class).setParameter("busId", b).
					setParameter("date", d).getSingleResult();
			System.out.println(s);
		}catch (NoResultException e) {
			s=new Seats(d,b.getCapacity());
			b.addSeats(s);
			mgr.merge(b);
		}
		return s;
	}

	@Override
	public String bookSeatsByBus(int busId, String date, int noOfSeats) {
		System.out.println("in dao seats impl");
		String jpql="select s from Seats s where s.busId = :busId and s.bookDate = :date";
		Bus b=mgr.find(Bus.class,busId);
		System.out.println(b);
		LocalDate d=DayFromDate.getLocalDate(date);
		System.out.println(d);
		Seats s=mgr.createQuery(jpql,Seats.class).setParameter("busId", b).setParameter("date", d).getSingleResult();
		System.out.println(s);
		if (noOfSeats < s.getAvailableSeats())
		{
			s.setAvailableSeats((byte)(s.getAvailableSeats() - noOfSeats));
			
		}
		mgr.merge(s);
		return "Seats booked Successfully..";
	}

	@Override
	public String addSeatsByBus(int busId, String date, int noOfSeats) {
		String jpql = "select s from Seats s where s.busId = :busId and s.bookDate = :date";
		Bus b=mgr.find(Bus.class,busId);
		LocalDate d=DayFromDate.getLocalDate(date);
		Seats s=mgr.createQuery(jpql, Seats.class).setParameter("busId", b).setParameter("date", d).getSingleResult();
		if (noOfSeats <s.getAvailableSeats())
		{
			s.setAvailableSeats((byte) (s.getAvailableSeats()+ noOfSeats));			
		}
		mgr.merge(s);
		return "Seats added successfully";
	}

	@Override
	public String removeSeatsByBus(int busId, String date) {
		String jpql = "select s from Seats s where s.busId = :busId and s.bookDate = :date";
		Bus b=mgr.find(Bus.class, busId);
		LocalDate d=DayFromDate.getLocalDate(date);
		Seats s=mgr.createQuery(jpql, Seats.class).setParameter("busId", b).setParameter("date", d).getSingleResult();
		b.removeSeats(s);
		mgr.merge(s);
		return "Seats removed successfully";
		
	}

}

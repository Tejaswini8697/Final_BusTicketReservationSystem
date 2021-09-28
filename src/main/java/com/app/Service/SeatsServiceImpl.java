package com.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ISeatsDao;
import com.app.pojos.Seats;

@Service
@Transactional
public class SeatsServiceImpl implements ISeatsService
{
	@Autowired
	ISeatsDao dao;

	@Override
	public Seats getSeatsByBusId(int busId, String date) {
		return dao.getSeatsByBusId(busId, date);
	}

	@Override
	public String bookSeatsByBus(int busId, String date, int noOfSeats) {
		return dao.addSeatsByBus(busId, date, noOfSeats);
	}

	@Override
	public String addSeatsByBus(int busId, String date, int noOfSeats) {
		return dao.addSeatsByBus(busId, date, noOfSeats);
	}

	@Override
	public String removeSeatsByBus(int busId, String date) {
		return dao.removeSeatsByBus(busId, date);
	}
	

}

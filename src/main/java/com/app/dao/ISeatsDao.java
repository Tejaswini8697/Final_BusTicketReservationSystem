package com.app.dao;

import com.app.pojos.Seats;

public interface ISeatsDao {
	public Seats getSeatsByBusId(int busId,String date);
	public String bookSeatsByBus(int busId, String date,int noOfSeats);
	public String addSeatsByBus(int busId, String date, int noOfSeats);
	public String removeSeatsByBus(int busId, String date);

}

package com.app.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.Service.IBusService;
import com.app.Service.ISeatsService;
import com.app.Service.ITicketService;
import com.app.pojos.Bus;
import com.app.pojos.Seats;
import com.app.pojos.Tickets;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	IBusService busService;

	@Autowired
	ITicketService ticketService;

	@Autowired
	ISeatsService seatService;

	@PostConstruct
	public void init() {
		System.out.println("Customer controller");
	}

	@GetMapping("/searchBus")
	public ResponseEntity<?> searchBus(@RequestParam String source, @RequestParam String destination,
			@RequestParam String date) {
		System.out.println("searchBus");
		try {
			return new ResponseEntity<List<Bus>>(busService.getBusByRoutes(source, destination, date), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Bus cannot be searched", HttpStatus.OK);
		}
	}
	
	@GetMapping("/viewSeats")
	public ResponseEntity<?> getSeatsByBus(@RequestParam String date, int busId) {
		System.out.println("In get seats by bys");
		try {
			return new ResponseEntity<Seats>(seatService.getSeatsByBusId(busId, date), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Seats for selected bus cannot be searched", HttpStatus.OK);
		}
	}
	
	@GetMapping("/bookSeats")
	public ResponseEntity<?> bookSeatsByBus(@RequestParam String date, int busId, int noOfSeats)
	{
		System.out.println("bookSeatsByBus");
		try {
			return new ResponseEntity<String>(seatService.bookSeatsByBus(busId, date, noOfSeats), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Seats for selected bus cannot be booked", HttpStatus.OK);
		}
	}
	
	@GetMapping("/addSeats")
	public ResponseEntity<?> addSeatsByBus(@RequestParam String date, int busId, int noOfSeats) {
		System.out.println("addSeatsByBus");
		try {
			return new ResponseEntity<String>(seatService.addSeatsByBus(busId, date, noOfSeats), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Seats for selected bus cannot be added", HttpStatus.OK);
		}
	}
	
	@GetMapping("/removeSeats")
	public ResponseEntity<?> removeSeatsByBus(@RequestParam String date, int busId) {
		System.out.println("removeSeatsByBus");
		try {
			return new ResponseEntity<String>(seatService.removeSeatsByBus(busId, date), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Seats for selected bus cannot be removed", HttpStatus.OK);
		}
	}

	@PostMapping("/bookTicket")
	public ResponseEntity<?> bookTicket(@RequestBody Tickets t, @RequestParam int userId, @RequestParam int busId)
	{
		System.out.println("bookTicket");
		try {
			return new ResponseEntity<Tickets>(ticketService.bookTickets(t, busId, userId), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Seats for selected bus cannot be booked", HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/cancelTicket/{ticketId}")
	public ResponseEntity<?> cancelTicket(@PathVariable int ticketId)
	{
		System.out.println("cancelTicket");
		try {
			return new ResponseEntity<Tickets>(ticketService.cancelTickets(ticketId), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Tickets cannot be cancelled", HttpStatus.OK);
		}
	}
	
	@GetMapping("/getUserTickets/{userId}")
	public ResponseEntity<?> getTicketsofUser(@PathVariable int userId)
	{
		System.out.println("getTicketsofUser");
		try {
			return new ResponseEntity<List<Tickets>>(ticketService.getTicketsOfUser(userId), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Tickets cannot be fetched for user", HttpStatus.OK);
		}
	}
	
	@GetMapping("/getBusTickets/")
	public ResponseEntity<?> getTicketsofBus(@RequestParam int busId, @RequestParam String date)
	{
		System.out.println("getTicketsofBus");
		try {
			return new ResponseEntity<List<Tickets>>(ticketService.getTicketsOfBus(busId, date), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Tickets cannot be fetched for Bus", HttpStatus.OK);
		}
	}
}

package com.app.pojos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Tickets {
	private Integer id;
	private Date bookedDate;
	private String OwnerName;
	private String ownerPhoneNo;
	private double fare;
	private Integer noOfSeats;
	private PaymentType paymentMode;
	private Bus busId;
	private User userId;
	private List<Passenger> passengers=new ArrayList<Passenger>();
	/*
	 * INSERT INTO Tickets (booked_Date, owner_Name, owner_Phone_no, fare, no_Of_Seats, payment_Mode, bus_Id, user_Id)
	 *  VALUES ("2020-01-26", "AMAN", 7587042615, 400, 1, "CASH", 2, 2);
	 */
	
	public Tickets() {
		System.out.println("Tickets constr");
	}
	public Tickets(Integer id, Date bookedDate, String ownerName, String ownerPhoneNo, double fare, Integer noOfSeats,
			PaymentType paymentMode) {
		super();
		this.id = id;
		this.bookedDate = bookedDate;
		this.OwnerName = ownerName;
		this.ownerPhoneNo = ownerPhoneNo;
		this.fare = fare;
		this.noOfSeats = noOfSeats;
		this.paymentMode = paymentMode;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	public Date getBookedDate() {
		return bookedDate;
	}
	public void setBookedDate(Date bookedDate) {
		this.bookedDate = bookedDate;
	}
	
	@Column(length = 30,nullable = false)
	public String getOwnerName() {
		return OwnerName;
	}
	public void setOwnerName(String ownerName) {
		OwnerName = ownerName.toUpperCase().trim();
	}
	
	@Column(nullable = false)
	public String getOwnerPhoneNo() {
		return ownerPhoneNo;
	}
	public void setOwnerPhoneNo(String ownerPhoneNo) {
		this.ownerPhoneNo = ownerPhoneNo;
	}
	
	@Column(nullable = false)
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	@Column(nullable = false)
	public Integer getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(Integer noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public PaymentType getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(PaymentType paymentMode) {
		this.paymentMode = paymentMode;
	}
	@ManyToOne
	@JoinColumn(name="busId")
	public Bus getBusId() {
		return busId;
	}
	public void setBusId(Bus busId) {
		this.busId = busId;
	}
	
	@ManyToOne(fetch=FetchType.LAZY,optional = false)
	@JoinColumn(name="userId")
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	@JsonIgnore
	@OneToMany(mappedBy = "ticketId",cascade = CascadeType.ALL,orphanRemoval = true)
	public List<Passenger> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}
	
	public void addPassenger(Passenger p)
	{
		this.passengers.add(p);
		p.setTicketId(this);
	}
	public void removePassenger(Passenger p)
	{
		this.passengers.remove(p);
		p.setTicketId(null);
	}
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", bookedDate=" + bookedDate + ", OwnerName=" + OwnerName + ", ownerPhoneNo="
				+ ownerPhoneNo + ", fare=" + fare + ", noOfSeats=" + noOfSeats + ", paymentMode=" + paymentMode + "]";
	}
	
	
	
	
}

package com.app.pojos;
import java.time.LocalDate;

import javax.persistence.*;

@Entity
public class Seats {
	private Integer id;
	private LocalDate bookDate;
	private Byte availableSeats;
	private Bus busId;
	
	/*insert into Seats (bookDate,availableSeats,busId) values ('2021-01-23',15,4);
	
	*/
	public Seats() {
		System.out.println("in seats constr");
		
	}

	public Seats(LocalDate bookDate, Byte availableSeats) {
		super();
		this.bookDate = bookDate;
		this.availableSeats = availableSeats;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(nullable = false)
	public LocalDate getBookDate() {
		return bookDate;
	}

	public void setBookDate(LocalDate bookDate) {
		this.bookDate = bookDate;
	}
	
	@Column(nullable = false)
	public Byte getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(Byte availableSeats) {
		this.availableSeats = availableSeats;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="busId")
	public Bus getBusId() {
		return busId;
	}

	public void setBusId(Bus busId) {
		this.busId = busId;
	}

	@Override
	public String toString() {
		return "Seats [id=" + id + ", bookDate=" + bookDate + ", availableSeats=" + availableSeats + ", busId=" + busId
				+ "]";
	}
	
	
	

}

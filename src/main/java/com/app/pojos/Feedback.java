package com.app.pojos;
import javax.persistence.*;

@Entity
public class Feedback {
	private Integer id;
	private byte rating;
	private String comment;
	private Bus busId;
	private User userId;
	
	public Feedback() {
		System.out.println("In FeedBack.");
	}
		
	public Feedback(byte rating, String comment) {
		super();
		this.rating = rating;
		this.comment = comment;
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
	public byte getRating() {
		return rating;
	}
	public void setRating(byte rating) {
		this.rating = rating;
	}
	
	@Column(length = 500)
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@ManyToOne
	@JoinColumn(name="busId")
	public Bus getBusId() {
		return busId;
	}
	public void setBusId(Bus busId) {
		this.busId = busId;
	}
	@ManyToOne
	@JoinColumn(name="userId")
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Feedback [id=" + id + ", rating=" + rating + ", comment=" + comment + "]";
	}
	
	
	
}

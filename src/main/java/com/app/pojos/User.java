package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	private Integer id;
	private String name;
	private String email;
	private String password;
	private SexType sex;
	private Long phone;
	private String city;
	private StateType state;
	private int pin;
	private CustomerRoleType role;
	private List<Tickets> tickets = new ArrayList<Tickets>();
	private List<Feedback> feedbacks = new ArrayList<Feedback>();

	/*
	 * INSERT INTO User (name, email, password, sex, phone, city, state, pin, role)
	 * values ('SHWETA SAH', 'shweta@gmail.com', '1234', 'FEMALE', 8223087766,
	 * 'RAIPUR', 'CHHATTISGARH', 492015, 'ADMIN'); INSERT INTO User (name, email,
	 * password, sex, phone, city, state, pin, role) values ('ANJALI ROOPCHANDANI',
	 * 'anjali@gmail.com', '5678', 'FEMALE', 8103736765, 'DURG', 'CHHATTISGARH',
	 * 490006, 'CUSTOMER'); INSERT INTO User (name, email, password, sex, phone,
	 * city, state, pin, role) values ('RATNENDRA PRATAP SINGH',
	 * 'ratnendra@gmail.com', '5678', 'MALE', 7587042615, 'RAIPUR', 'CHHATTISGARH',
	 * 492001, 'CUSTOMER');
	 */

	public User() {
		System.out.println("in User");
	}

	public User(String name, String email, String password, SexType sex, Long phone, String city, StateType state,
			int pin, CustomerRoleType role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.sex = sex;
		this.phone = phone;
		this.city = city;
		this.state = state;
		this.pin = pin;
		this.role = role;
	}

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public User(String email, Long phone) {
		super();
		this.email = email;
		this.phone = phone;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 30, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.toUpperCase().trim();
	}

	@Column(length = 30, nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.toLowerCase().trim();
	}

	@Column(length = 30, nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password.trim();
	}

	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	public SexType getSex() {
		return sex;
	}

	public void setSex(SexType sex) {
		this.sex = sex;
	}

	@Column(nullable = false)
	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	@Column(length = 30, nullable = false)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city.toUpperCase().trim();
	}

	@Enumerated(EnumType.STRING)
	@Column(length = 30, nullable = false)
	public StateType getState() {
		return state;
	}

	public void setState(StateType state) {
		this.state = state;
	}

	@Column(nullable = false)
	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	public CustomerRoleType getRole() {
		return role;
	}

	public void setRole(CustomerRoleType role) {
		this.role = role;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Tickets> getTickets() {
		return tickets;
	}

	public void setTickets(List<Tickets> tickets) {
		this.tickets = tickets;
	}

	public void addTickets(Tickets tickets) {
		this.tickets.add(tickets);
		tickets.setUserId(this);
	}

	public void removeTickets(Tickets tickets) {
		this.tickets.remove(tickets);
		tickets.setUserId(null);
	}

	@JsonIgnore
	@OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public void addFeedback(Feedback f) {
		this.feedbacks.add(f);
		f.setUserId(this);
	}

	public void removeFeedback(Feedback f) {
		this.feedbacks.remove(f);
		f.setUserId(null);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", sex=" + sex
				+ ", phone=" + phone + ", city=" + city + ", state=" + state + ", pin=" + pin + ", role=" + role + "]";
	}

}

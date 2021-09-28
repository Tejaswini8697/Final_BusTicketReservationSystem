package com.app.pojos;
import javax.persistence.*;

@Entity
public class Passenger {
	private Integer id;
	private String name;
	private SexType sex;
	private Integer age;
	private Tickets ticketId;
		
	public Passenger() {
		System.out.println("In passenger");
	}
	public Passenger( String name, SexType sex, Integer age) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length = 30,nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Enumerated(EnumType.STRING)
	@Column(length = 10,nullable = false)
	public SexType getSex() {
		return sex;
	}
	public void setSex(SexType sex) {
		this.sex = sex;
	}
	@Column(nullable = false)
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@ManyToOne
	@JoinColumn(name="ticketId")
	public Tickets getTicketId() {
		return ticketId;
	}
	public void setTicketId(Tickets ticketId) {
		this.ticketId = ticketId;
	}
	@Override
	public String toString() {
		return "Passenger [id=" + id + ", name=" + name + ", sex=" + sex + ", age=" + age + ", ticketId=" + ticketId
				+ "]";
	}
	
	
	
	

}

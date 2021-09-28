package com.app.pojos;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Routes {
	private Integer id;
	private String source;
	private StateType sourceState;
	private String Destination;
	private StateType DestinationState;
	private List<Bus> buses=new ArrayList<Bus>();
	
	/*
	 * 
INSERT INTO Routes (source, source_State, destination, destination_State) VALUES ('PUNE', 'MAHARASHTRA', 'BANGALORE', 'KARNATAKA');
INSERT INTO Routes (source, source_State, destination, destination_State) VALUES ('PUNE', 'MAHARASHTRA', 'NAGPUR', 'MAHARASHTRA');
INSERT INTO Routes (source, source_State, destination, destination_State) VALUES ('NAGPUR', 'MAHARASHTRA', 'PUNE', 'MAHARASHTRA');
INSERT INTO Routes (source, source_State, destination, destination_State) VALUES ('BANGALORE', 'KARNATAKA', 'PUNE', 'MAHARASHTRA');
INSERT INTO Routes (source, source_State, destination, destination_State) VALUES ('NAGPUR', 'MAHARASHTRA', 'BANGALORE', 'KARNATAKA');
INSERT INTO Routes (source, source_State, destination, destination_State) VALUES ('MUMBAI', 'MAHARASHTRA', 'BANGALORE', 'KARNATAKA');
	 * 
	 */
	public Routes() {
		System.out.println("In Routes");
	}
	public Routes(String source, StateType sourceState, String destination, StateType DestinationState) {
		super();
		this.source = source.toUpperCase().trim();
		this.sourceState = sourceState;
		this.Destination = destination.toUpperCase();
		this.DestinationState = DestinationState;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column (length = 25,nullable = false)
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	@Enumerated(EnumType.STRING)
	@Column(length = 25, nullable = false)
	public StateType getSourceState() {
		return sourceState;
	}
	public void setSourceState(StateType sourceState) {
		this.sourceState = sourceState;
	}
	
	@Column(length = 25, nullable = false)
	public String getDestination() {
		return Destination;
	}
	public void setDestination(String destination) {
		Destination = destination;
	}
	@Enumerated(EnumType.STRING)
	@Column(length = 25, nullable = false)
	public StateType getDestinationState() {
		return DestinationState;
	}
	public void setDestinationState(StateType sourceDestination) {
		this.DestinationState = sourceDestination;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "routeId", orphanRemoval = true)
	@JsonManagedReference
	public List<Bus> getBuses() {
		return buses;
	}
	public void setBuses(List<Bus> buses) {
		this.buses = buses;
	}
	public void addBus(Bus b)
	{
		System.out.println("in routes add bus");
		this.buses.add(b);
		b.setRouteId(this);
		System.out.println(b.getRouteId());
	}
	public void removeBus(Bus b)
	{
		this.buses.remove(b);
		b.setRouteId(null);
	}
	@Override
	public String toString() {
		return "Routes [id=" + id + ", source=" + source + ", sourceState=" + sourceState + ", Destination="
				+ Destination + ", sourceDestination=" + DestinationState + "]";
	}
	
	
}

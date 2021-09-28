package com.app.dao;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.app.pojos.Bus;
import com.app.pojos.DayFromDate;
import com.app.pojos.Routes;

@Repository
public class BusDaoImpl implements IBusDao {
	
	@PersistenceContext
	private EntityManager mgr;

	@Override
	public List<Bus> getAllBus() {
		String jpql="select b from Bus b";
		return mgr.createQuery(jpql, Bus.class).getResultList();
	}

	@Override
	public Bus getBusById(int id) {
		String jpql = "select b from Bus b where b.id = :id";
		return mgr.createQuery(jpql, Bus.class).setParameter("id", id).getSingleResult();
	}

	@Override
	public Bus addBus(Bus b,int routeId) {
		Routes r=mgr.find(Routes.class,routeId);
		System.out.println(" Route is " + r);
		System.out.println("Bus is " + b);
		//r.addBus(b);
		b.setRouteId(r);
		System.out.println("route added");
		System.out.println("final bus is " + b);
		mgr.persist(b);
		return b;
		
	}

	@Override
	public String removeBus(int id) {
		Bus b=mgr.find(Bus.class,id);
		System.out.println(b);
		/*Routes r=mgr.find(Routes.class,b.getRouteId().getId());
		System.out.println(r);
		r.removeBus(b);
		System.out.println("route removed successfully");*/
		mgr.remove(b);
		System.out.println(b);
		return "Bus Removed successfully..";		
	}

	@Override
	public String updateBus(Bus b) {
		System.out.println("in dao impl");
		Bus oldBus=mgr.find(Bus.class,b.getId());
		System.out.println("initial old bus " + oldBus);
		oldBus.setCapacity(b.getCapacity());
		System.out.println("capacity successfully updated");
		oldBus.setType(b.getType());
		System.out.println("type successfully updated");
		oldBus.setArrivalDate(b.getArrivalDate());
		System.out.println("arrival successfully updated");
		oldBus.setDestinationDate(b.getDestinationDate());
		System.out.println("destination successfully updated");
		oldBus.setPrice(b.getPrice());
		System.out.println("price successfully updated");
		System.out.println("old bus is " + oldBus);
		mgr.merge(oldBus);
		return "Bus updated Successfully";
	}

	@Override
	public List<Bus> getBusByRoutes(String source, String destination, String date) {
		System.out.println("in get bus by routes");
		System.out.println(source);
		System.out.println(destination);
		System.out.println(date);
		int day=DayFromDate.getDayInt(date);
		List<Bus> buses=new ArrayList<Bus>();
		String jpql="select r from Routes r where r.source=:source and r.destination=:destination";
		Routes r=mgr.createQuery(jpql,Routes.class).setParameter("source", source).setParameter("destination",destination).getSingleResult();
		System.out.println(r);
		int route_id=r.getId();
		System.out.println(route_id);
		String jpql2 = "select b from Bus b where b.routeId = :routeId";
		List<Bus> list = mgr.createQuery(jpql2, Bus.class).setParameter("routeId", r).getResultList();
		System.out.println(list);
		System.out.println("listing");
		for (Bus b:list)
		{
			System.out.println(b);
			b.toString();
			
			buses.add(b);
		}
		return buses;
		
	}
}

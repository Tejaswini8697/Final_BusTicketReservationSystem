package com.app.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IBusDao;
import com.app.pojos.Bus;

@Service
@Transactional
public class BusServiceImpl implements IBusService {

		@Autowired
		IBusDao dao;
	
	@Override
	public List<Bus> getAllBus() {
		return dao.getAllBus();
	}

	@Override
	public Bus getBusById(int id) {
		return dao.getBusById(id);
	}

	@Override
	public Bus addBus(Bus b, int routeId) {
		return dao.addBus(b, routeId);
	}

	@Override
	public String removeBus(int id) {
		return dao.removeBus(id);
	}

	@Override
	public String updateBus(Bus b) {
		System.out.println("in bus service");
		return dao.updateBus(b);
	}

	@Override
	public List<Bus> getBusByRoutes(String source, String destination, String date) {
		return dao.getBusByRoutes(source.trim().toUpperCase(), destination.trim().toUpperCase(), date);
	}
	
	
 
}

package com.app.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IRoutesDao;
import com.app.pojos.Routes;

@Service
@Transactional
public class RouteServiceImpl implements IRouteService {

	@Autowired
	IRoutesDao dao;
	
	@Override
	public List<Routes> getAllRoutes() {
		return dao.getAllRoutes();
	}

	@Override
	public Routes getRouteById(int id) {
		return dao.getRouteById(id);
	}

	@Override
	public Routes addRoute(Routes r) {
		return dao.addRoute(r);
	}

	@Override
	public String removeRoutes(int id) {
		return dao.removeRoutes(id);
	}

	@Override
	public String updateRoutes(Routes r) {
		return dao.updateRoutes(r);
	}

	
}

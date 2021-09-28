package com.app.Service;

import java.util.List;

import com.app.pojos.Routes;

public interface IRouteService {
	public List<Routes> getAllRoutes();
	public Routes getRouteById(int id);
	public Routes addRoute(Routes r);
	public String removeRoutes(int id);
	public String updateRoutes(Routes r);
}

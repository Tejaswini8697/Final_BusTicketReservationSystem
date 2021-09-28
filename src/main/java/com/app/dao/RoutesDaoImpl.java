package com.app.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.app.pojos.Routes;

@Repository
public class RoutesDaoImpl implements IRoutesDao {

	@PersistenceContext
	private EntityManager mgr;
	
	@Override
	public List<Routes> getAllRoutes() {
		String jpql = "select r from Routes r";
		List<Routes> routes= mgr.createQuery(jpql, Routes.class).getResultList();
		return routes;
	}

	@Override
	public Routes getRouteById(int id) {
		String jpql="select r from Routes r left outer join fetch r.buses where r.id = :id";
		return mgr.createQuery(jpql,Routes.class).setParameter("id",id).getSingleResult();
	}

	@Override
	public Routes addRoute(Routes r) {
		mgr.persist(r);
		return r;
	}
	
	@Override
	public String removeRoutes(int id) {
		Routes r = mgr.find(Routes.class, id);
		mgr.remove(r);
		return "Routes removed successfully";
	}

	@Override
	public String updateRoutes(Routes r) {
	mgr.merge(r);
	return "Route updates successfully..";
	}

}

package com.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.app.pojos.User;

@Repository
public class UserDaoImpl implements IUserDao {

	@PersistenceContext
	private EntityManager mgr;

	@Override
	public User validateUser(String email, String pass) {
		String jpql = "select u from User u where u.email=:email and u.password = :pass";
		return mgr.createQuery(jpql, User.class).setParameter("email", email).setParameter("pass", pass)
				.getSingleResult();
	}

	@Override
	public User getUser(String email) {
		String jpql = "select u from User u where u.email = :email";
		return mgr.createQuery(jpql, User.class).setParameter("email", email).getSingleResult();
	}

	@Override
	public void registerUser(User u) {
		System.out.println("In register User");
		mgr.persist(u);

	}

	@Override
	public void updatePassword(String email, String newPass) {
		System.out.println("In update password");
		String jpql = "select u from User u where u.email = :email";
		User u = mgr.createQuery(jpql, User.class).setParameter("email", email).getSingleResult();
		u.setPassword(newPass);
		mgr.merge(u);
	}

	@Override
	public void updateUser(User u) {
		System.out.println("in Update user");
		mgr.merge(u);

	}

	@Override
	public void updatePhone(String email, Long newPhone) {
		System.out.println("In update phone");
		String jpql = "select u from User u where u.email = :email";
		User oldUser =mgr.createQuery(jpql, User.class).setParameter("email", email).getSingleResult();
		oldUser.setPhone(newPhone);
		mgr.merge(oldUser);
	}

}

package es.formacion.cip.ejemplo4.dao;

import java.util.List;

import es.formacion.cip.ejemplo4.model.User;

public interface UserDao {

	User findByName(String name);
	
	List<User> findAll();

}
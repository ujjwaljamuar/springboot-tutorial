package com.jpa.test.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jpa.test.entities.User;

// extend crudrepo for crud operation with generic <Datatype, id type>
public interface UserRepository extends CrudRepository<User, Integer>{
	
	// custom methods
	public List<User> findByName(String name);
	
	public List<User> findByAge(int age);
	
	public List<User> findBySex(char sex);
	
	public List<User> findBySexAndAge(char sex, int age);
	
	public List<User> findByNameStartingWith(String prefix);
	
	public List<User> findByNameEndingWith(String suffix);
	
	public List<User> findByNameContaining(String word);
	
	public List<User> findByAgeLessThan(int age);	
	
	// JPQL queries
	@Query("SELECT u FROM User u")
	public List<User> getAllUsers();
	
	@Query("SELECT u FROM User u WHERE u.name = :n AND u.age = :a")
	public List<User> getUserByName(@Param("n") String name,@Param("a") int age);
	
	// Native Query
	@Query(value="SELECT * FROM User", nativeQuery=true)
	public List<User> getAllUsersList();
}

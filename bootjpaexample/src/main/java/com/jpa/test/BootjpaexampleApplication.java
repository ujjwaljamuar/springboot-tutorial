package com.jpa.test;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.jpa.test.dao.UserRepository;
import com.jpa.test.entities.User;

@SpringBootApplication
public class BootjpaexampleApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(BootjpaexampleApplication.class, args);
		
		// create db entity using userRepository
		UserRepository userRepository = context.getBean(UserRepository.class);
		
		
		// CREATE operations
		 
		// create user object
		User user1 = new User();
		user1.setName("Ujjwal Jamuar");
		user1.setAge(22);
		user1.setSex('M');
		
		// saving single user
//		User user1 = userRepository.save(user);
		
		// creating multiple users
		
		User user2 = new User();
		user2.setName("Nivedita Jamuar");
		user2.setAge(26);
		user2.setSex('F');
		
		User user3 = new User();
		user3.setName("Nilu Jamuar");
		user3.setAge(48);
		user3.setSex('F');
		
		User user4 = new User();
		user4.setName("Birendra Jamuar");
		user4.setAge(58);
		user4.setSex('M');
		
		List<User> usersList = List.of(user1, user2, user3, user4);
		
		Iterable<User> savedUsers = userRepository.saveAll(usersList);	
		
		savedUsers.forEach( users -> {
			System.out.println(users);
		});
		
		
		
		// UPDATE operation
		
		// find or get the user if exist
		try {
			Optional<User> optional = userRepository.findById(11);
			
			// check if exist
			User user = optional.get();
			
			System.out.println("Founded User \n" + user);
			
			user.setName("Ujjwal Jamuar");
			
			User updatedUser = userRepository.save(user);
			
			System.out.println("Updated User \n" + updatedUser);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		// DELETE operations
		
		// by user id;
		try {
			userRepository.deleteById(4);
			System.out.println("Deleted");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		
		// by user entity
		Optional<User> optional = userRepository.findById(1);
		User findUserToDelete = optional.get();
		userRepository.delete(findUserToDelete);
		
		// delete all user
		userRepository.deleteAll();
		
		// delete all using iterable of users
		Iterable<User> allUsersList = userRepository.findAll();
		userRepository.deleteAll(allUsersList);
		
		// delete all using iterable of ids
		userRepository.deleteAllById(null);
		
		
		// custom methods
		
//		List<User> userByName = userRepository.findByName("Ujjwal Jamuar");
		
//		List<User> userByAge = userRepository.findByAge(48);
//		List<User> userBySex = userRepository.findBySex('F');
//		List<User> userBySexAndAge = userRepository.findBySexAndAge('M', 21);
//		List<User> userByNameStartingWith = userRepository.findByNameStartingWith("N");
//		List<User> userByNameContaining = userRepository.findByNameContaining("Jamuar");
//		List<User> userByAgeLessThan = userRepository.findByAgeLessThan(30);
		
//		List<User> allUsers = userRepository.getAllUsers();
		
//		List<User> allUsers = userRepository.getUserByName("Ujjwal Jamuar", 22);
		
		List<User> allUsers = userRepository.getAllUsersList();
		
		allUsers.forEach(user ->  System.out.println(user));
		
	}

}

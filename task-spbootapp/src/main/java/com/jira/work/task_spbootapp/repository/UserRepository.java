package com.jira.work.task_spbootapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jira.work.task_spbootapp.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query(value = "SELECT u FROM User u WHERE u.username = :username")
	User findUserByUsername(@Param("username") String username);
	
	Optional<User> findByEmail(String email);
	
	boolean existsByUsernameAndPassword(String username,String password);
	
	boolean existsByEmailAndPassword(String email,String password);

}

//This gives access to many pre-built methods like:
//	without writing SQL or JPQL.
//* `findAll()` gets a list of all users.
//* `findById(Long id)` fetches a user by their ID.
//* `save(User user)` inserts or updates a user.
//* `deleteById(Long id)` removes a user by ID.
//* `existsById(Long id)` checks if a user exists by ID.
//* `count()` returns the total number of users.



//
//Optional: A Java container object used to avoid null pointer exceptions.
//
//JpaRepository: Provides built-in CRUD and query methods for interacting with the database.
//
//@Query: Allows custom JPQL queries.
//
//@Param: Binds method parameters to named parameters in @Query.
//
//User: The JPA entity (model) representing the Users table.


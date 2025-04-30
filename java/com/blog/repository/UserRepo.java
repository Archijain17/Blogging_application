package com.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blog.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>{

	Optional<User> findByEmail(String email) ;

	@Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.id = :userId")
	Optional<User> findByIdWithRoles(@Param("userId") int userId);
}

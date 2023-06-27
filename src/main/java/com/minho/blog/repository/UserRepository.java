package com.minho.blog.repository;

import com.minho.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);


}


//JPA Naming 원리
//SELECT * FROM user WHERE username = ?1 AND password = ?2;
//    User findByUsernameAndPassword(String username, String password);
//
//    @Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//    User login(String username, String password);
package com.airport.repository.springdatajpa;

import com.airport.model.User;

import com.airport.repository.UserRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;


public interface SpringDataUserRepository extends UserRepository, Repository<User, Integer> {
    @Query("SELECT DISTINCT user FROM User user WHERE user.login LIKE :login%")
    public User findByLogin(@Param("login") String login);

    @Query("SELECT user FROM User user WHERE user.id = :id")
    public User findById(@Param("id") int id);
}

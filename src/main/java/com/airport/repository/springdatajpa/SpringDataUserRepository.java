package com.airport.repository.springdatajpa;

import com.airport.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


public interface SpringDataUserRepository extends PagingAndSortingRepository<User, Integer> {
    @Query("SELECT DISTINCT user FROM User user  WHERE user.login LIKE :login%")
    public User findByLogin(@Param("login") String login);

    @Query("SELECT user FROM User user JOIN FETCH user.staff WHERE user.id = :id")
    public User findById(@Param("id") int id);

}

package com.airport.service;

import com.airport.model.User;
import org.springframework.dao.DataAccessException;

/**
 * Created by Vic on 01.01.2015.
 */
public interface AirportService {

    public User findUserByLogin(String login) throws DataAccessException;

    public User findUserById(int id) throws DataAccessException;

    public void saveUser(User user) throws DataAccessException;
}

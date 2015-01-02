package com.airport.service;

import com.airport.model.User;
import com.airport.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AirportServiceImpl implements AirportService {

    private UserRepository userRepository;

    @Autowired
    public AirportServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserById(int id) throws DataAccessException {
        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByLogin(String login) throws DataAccessException {
        return userRepository.findByLogin(login);
    }

    @Override
    @Transactional
    public void saveUser(User user) throws DataAccessException {
        userRepository.save(user);
    }
}

package com.airport.service;

import com.airport.model.Department;
import com.airport.model.Staff;
import com.airport.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Vic on 01.01.2015.
 */
public interface AirportService {

    public User findUserByLogin(String login) throws DataAccessException;

    public User findUserById(int id) throws DataAccessException;

    public Page<User> getUsers(Pageable pageable) throws DataAccessException;

    public void saveUser(User user) throws DataAccessException;

    public Staff findStaffById(int id) throws DataAccessException;

    public Page<Staff> getStaffs(Pageable pageable) throws DataAccessException;

    public void saveStaff(Staff staff) throws DataAccessException;

    public Page<Department> getDepartments(Pageable pageable) throws DataAccessException;

    public Department findDepartmentById(int id) throws DataAccessException;
}

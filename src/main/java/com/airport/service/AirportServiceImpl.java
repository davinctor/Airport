package com.airport.service;

import com.airport.model.Department;
import com.airport.model.Staff;
import com.airport.model.User;
import com.airport.repository.springdatajpa.SpringDataDepartmentRepository;
import com.airport.repository.springdatajpa.SpringDataStaffRepository;
import com.airport.repository.springdatajpa.SpringDataUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AirportServiceImpl implements AirportService {

    private SpringDataUserRepository userRepository;
    private SpringDataStaffRepository staffRepository;
    private SpringDataDepartmentRepository departmentRepository;

    @Autowired
    public AirportServiceImpl(SpringDataUserRepository userRepository,
                              SpringDataStaffRepository staffRepository,
                              SpringDataDepartmentRepository departmentRepository) {
        this.userRepository = userRepository;
        this.staffRepository = staffRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserById(int id) throws DataAccessException {
        User user = userRepository.findById(id);
        user.getStaff().getPhones().size();
        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByLogin(String login) throws DataAccessException {
        return userRepository.findByLogin(login);
    }

    @Override
    public void saveUser(User user) throws DataAccessException {
        userRepository.save(user);
    }


    @Override
    @Transactional
    public Page<User> getUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        for (User user : users.getContent())
            user.getStaff().getPhones().size();
        return userRepository.findAll(pageable);
    }

    @Override
    public Staff findStaffById(int id) throws DataAccessException {
        return staffRepository.findById(id);
    }

    @Override
    public Page<Staff> getStaffs(Pageable pageable) {
        Page<Staff> staffs = staffRepository.findAll(pageable);
        for (Staff staff : staffs.getContent())
            staff.getPhones().size();
        return staffs;
    }

    @Override
    public void saveStaff(Staff staff) throws DataAccessException {
        staffRepository.save(staff);
    }

    @Override
    public Page<Department> getDepartments(Pageable pageable) throws DataAccessException {
        Page<Department> departments = departmentRepository.findAll(pageable);
        return departments;
    }

    @Override
    public Department findDepartmentById(int id) throws DataAccessException {
        return departmentRepository.findDepartmentById(id);
    }
}

package com.airport.service;

import com.airport.model.Department;
import com.airport.model.Phone;
import com.airport.model.Staff;
import com.airport.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Vic on 01.01.2015.
 */
public interface AirportService {

    public User findUserByLogin(String login);

    public User findUserByFullLogin(String login);

    public Page<User> findUsersByLogin(String login, Pageable pageable);

    public User findUserByIdWithPhones(int id);

    public User findUserById(int id);

    public Page<User> findUsersByRole(String role, Pageable pageable);

    public Page<User> findUsersByRoleAndLogin(String role,
                                              String login, Pageable pageable);

    public Page<User> getUsers(Pageable pageable);

    public void saveUser(User user);

    public void deleteUserById(int id);

    public Staff findStaffById(int id);

    public Staff findStaffByIdWithPhones(int id);

    public Staff findStaffByUserId(int id);

    public Page<Staff> getStaffs(Pageable pageable);

    public List<Staff> getStaffs();

    public void saveStaff(Staff staff);

    public void deleteStaffById(int id);

    public void deleteStaffsByDepartmentId(int id);

    public List<Department> getAllDepartments();

    public Page<Department> getDepartments(Pageable pageable);

    public List<Department> getDepartmentsWithInitUnregisteredStaff();

    public Department findDepartmentById(int id);

    public Department findDepartmentByIdWithStaffs(int id);

    public void saveDepartment(Department department);

    public void deleteDepartmentById(int id);

    public void savePhone(Phone phone);
}

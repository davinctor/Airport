package com.airport.service;

import com.airport.model.Department;
import com.airport.model.Phone;
import com.airport.model.Staff;
import com.airport.model.User;
import com.airport.repository.springdatajpa.SpringDataDepartmentRepository;
import com.airport.repository.springdatajpa.SpringDataPhoneRepository;
import com.airport.repository.springdatajpa.SpringDataStaffRepository;
import com.airport.repository.springdatajpa.SpringDataUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, timeout = 60)
public class AirportServiceImpl implements AirportService {

    private SpringDataUserRepository userRepository;
    private SpringDataStaffRepository staffRepository;
    private SpringDataDepartmentRepository departmentRepository;
    private SpringDataPhoneRepository phoneRepository;

    @Autowired
    public AirportServiceImpl(SpringDataUserRepository userRepository,
                              SpringDataStaffRepository staffRepository,
                              SpringDataDepartmentRepository departmentRepository,
                              SpringDataPhoneRepository phoneRepository) {
        this.userRepository = userRepository;
        this.staffRepository = staffRepository;
        this.departmentRepository = departmentRepository;
        this.phoneRepository = phoneRepository;
    }

    @Override
    public User findUserById(int id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findUserByIdWithStaff(int id) {
        return userRepository.findByIdWithStaff(id);
    }

    @Override
    public User findUserByFullLogin(String login) {
        return userRepository.findByFullLogin(login);
    }

    @Override
    public Page<User> findUsersByLogin(String login, Pageable pageable) {
        return userRepository.findUsersByLogin(login, pageable);
    }

    @Override
    public Page<User> findUsersByRole(String role, Pageable pageable) {
        return userRepository.findUsersByRoleOfUser(role, pageable);
    }

    @Override
    public Page<User> findUsersByRoleAndLogin(String role,
                                              String login, Pageable pageable) {
        return userRepository.findUsersByRoleOfUserAndLogin(role, login, pageable);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteUserById(id);
    }

    @Override
    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Staff findStaffByIdWithPhones(int id) {
        Staff staff = staffRepository.findById(id);
        if (staff != null)
            staff.getPhones().size();
        return staff;
    }

    @Override
    public Staff findStaffByUserId(int id) {
        return staffRepository.findStaffByUserId(id);
    }

    @Override
    public Staff findStaffById(int id) {
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
    public List<Staff> getStaffs() {
        return staffRepository.findAllStaffs();
    }

    @Override
    public void saveStaff(Staff staff) {
        staffRepository.save(staff);
    }

    @Override
    public void deleteStaffById(int id) {
        staffRepository.deleteStaffById(id);
    }

    @Override
    public void deleteStaffsByDepartmentId(int id) {
        staffRepository.deleteStaffsByDepartmentId(id);
    }

    @Override
    public List<Department> getAllDepartments() {
        return makeCollection(departmentRepository.findAll());
    }

    @Override
    public Page<Department> getDepartments(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }

    @Override
    public List<Department> getDepartmentsWithStaffNullUser(int includedUserId) {
        List<Department> fromDatabase;

        if (includedUserId == WITHOUT_CUR_STAFF)
            fromDatabase = departmentRepository.findAllDepartmentsWithStaffsNullUser();
        else
            fromDatabase = departmentRepository.findAllDepartmentsWithStaffsNullUser(includedUserId);

        List<Staff> staffsResult;
        for (Department dep : fromDatabase) {
            staffsResult = new ArrayList<Staff>(dep.getStaffs().size());
            for (Staff staff : dep.getStaffs())
                if (staff.getUser() == null || staff.getUser().getId() == includedUserId)
                    staffsResult.add(staff);
            dep.getStaffs().clear();
            dep.getStaffs().addAll(staffsResult);
        }

        return fromDatabase;
    }

    @Override
    public Department findDepartmentById(int id) {
        return departmentRepository.findDepartmentById(id);
    }

    @Override
    public Department findDepartmentByIdWithStaffs(int id) {
        Department department = departmentRepository.findDepartmentById(id);
        if (department != null)
            department.getStaffs().size();
        return department;
    }

    @Override
    public void saveDepartment(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void deleteDepartmentById(int id) {
        departmentRepository.deleteDepartmentById(id);
    }

    @Override
    public Collection<Phone> getStaffPhones(int staffId) {
        return phoneRepository.getStaffPhones(staffId);
    }

    @Override
    public void savePhone(Phone phone) {
        phoneRepository.save(phone);
    }

    @Override
    public void deletePhone(Phone phone) {
        phoneRepository.delete(phone);
    }

    @Override
    public void deletePhoneById(int id) {
        phoneRepository.delete(id);
    }

    public static <E> List<E> makeCollection(Iterable<E> iter) {
        List<E> list = new ArrayList<E>();
        for (E item : iter)
            list.add(item);
        return list;
    }


}

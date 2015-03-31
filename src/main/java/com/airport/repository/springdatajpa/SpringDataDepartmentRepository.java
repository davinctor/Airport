package com.airport.repository.springdatajpa;

import com.airport.model.Department;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SpringDataDepartmentRepository extends PagingAndSortingRepository<Department, Integer> {

    @Query("SELECT department FROM Department department WHERE department.id = :id")
    public Department findDepartmentById(@Param("id") int id);

    @Query("SELECT DISTINCT d FROM Department d INNER JOIN d.staffs s " +
            "WHERE d.id = s.department.id AND s.user IS NULL")
    public List<Department> findAllDepartmentsWithStaffsNullUser();

    @Query("SELECT DISTINCT d FROM Department d INNER JOIN d.staffs s " +
            "WHERE d.id = s.department.id " +
            "AND ( s.user IS NULL OR s.user.id = :id )")
    public List<Department> findAllDepartmentsWithStaffsNullUser(@Param("id") int includedUserId);

    @Modifying
    @Query("DELETE FROM Department dep WHERE dep.id = :id")
    public void deleteDepartmentById(@Param("id") int id);

}
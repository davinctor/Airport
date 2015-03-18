package com.airport.repository.springdatajpa;

import com.airport.model.Department;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Vic on 21.01.2015.
 */
public interface SpringDataDepartmentRepository extends PagingAndSortingRepository<Department, Integer> {
    @Query("SELECT department FROM Department department WHERE department.id = :id")
    public Department findDepartmentById(@Param("id") int id);

    @Query("SELECT dep FROM Department dep")
    public List<Department> findAllDepartments();

    @Modifying
    @Query("DELETE FROM Department dep WHERE dep.id = :id")
    public void deleteDepartmentById(@Param("id") int id);
}

package com.airport.repository.springdatajpa;

import com.airport.model.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Vic on 21.01.2015.
 */
public interface SpringDataDepartmentRepository extends PagingAndSortingRepository<Department, Integer> {
    @Query("SELECT department FROM Department department WHERE department.id = :id")
    public Department findDepartmentById(@Param("id") int id);
}

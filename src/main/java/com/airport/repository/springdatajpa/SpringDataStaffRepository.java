package com.airport.repository.springdatajpa;

import com.airport.model.Staff;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Vic on 21.01.2015.
 */
public interface SpringDataStaffRepository extends PagingAndSortingRepository<Staff, Integer> {
    @Query("SELECT DISTINCT staff FROM Staff staff JOIN FETCH staff.phones WHERE staff.id = :id")
    public Staff findById(@Param("id") int id);
}

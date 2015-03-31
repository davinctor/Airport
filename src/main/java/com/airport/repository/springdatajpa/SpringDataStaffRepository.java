package com.airport.repository.springdatajpa;

import com.airport.model.Staff;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Vic on 21.01.2015.
 */
public interface SpringDataStaffRepository extends PagingAndSortingRepository<Staff, Integer> {
    @Query("SELECT DISTINCT staff FROM Staff staff WHERE staff.id = :id")
    public Staff findById(@Param("id") int id);

    @Query("SELECT staff FROM Staff staff WHERE staff.user.id = :id")
    public Staff findStaffByUserId(@Param("id") int id);

    @Query("SELECT staff FROM Staff staff")
    public List<Staff> findAllStaffs();

    @Modifying
    @Query("DELETE FROM Staff staff WHERE staff.id = :id")
    public void deleteStaffById(@Param("id") int id);

    @Modifying
    @Query("DELETE FROM Staff staff WHERE staff.department.id = :id")
    public void deleteStaffsByDepartmentId(@Param("id") int id);
}

package com.airport.repository.springdatajpa;

import com.airport.model.Phone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;


public interface SpringDataPhoneRepository extends CrudRepository<Phone, Integer> {

    @Query("SELECT phone FROM Phone phone WHERE phone.staff.id = :id")
    public Collection<Phone> getStaffPhones(@Param("id") int id);

}

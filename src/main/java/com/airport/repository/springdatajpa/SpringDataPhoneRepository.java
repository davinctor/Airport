package com.airport.repository.springdatajpa;

import com.airport.model.Phone;
import org.springframework.data.repository.CrudRepository;


public interface SpringDataPhoneRepository extends CrudRepository<Phone, Integer> {
}

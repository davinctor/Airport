package com.airport.repository.springdatajpa;

import com.airport.model.Phone;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Vic on 22.02.2015.
 */
public interface SpringDataPhoneRepository extends CrudRepository<Phone, Integer> {
}

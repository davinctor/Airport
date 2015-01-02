package com.airport.repository;

import com.airport.model.User;
import org.springframework.dao.DataAccessException;

/**
 * Repository interface for <code>User</code> domain objects
 */
public interface UserRepository {
    /**
     * Retrieve <code>User</code> from the data store by login, returning user whose login <i>starts</i>
     * with the given name.
     *
     * @param login Value to search for
     * @return a <code>User</code> of matching <code>User</code> (or <code>null</code> if none
     * found)
     * @throws org.springframework.dao.DataAccessException if not found
     */
    User findByLogin(String login) throws DataAccessException;

    /**
     * Retrieve <code>User</code> from the data store by id.
     *
     * @param id Value to search for
     * @return a <code>User</code> if found
     * @throws org.springframework.dao.DataAccessException if not found
     */
    User findById(int id) throws DataAccessException;


    /**
     * Save an <code>User</code> to the data store, either
     * inserting or updating
     *
     * @param user the <code>User</code> to save
     * @throws DataAccessException if cant save
     */
    void save(User user) throws DataAccessException;
}

package com.airport.repository.jpa;

import com.airport.model.User;
import com.airport.repository.UserRepository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by Vic on 02.01.2015.
 */
@Repository
public class JpaUserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findByLogin(String login) {
        Query query = entityManager.createQuery("SELECT DISTINCT user FROM User user WHERE user.login LIKE :login");
        query.setParameter("login", login + "%");
        return (User) query.getSingleResult();
    }

    @Override
    public User findById(int id) {
        Query query = entityManager.createQuery("SELECT user FROM User user WHERE user.id = :id");
        query.setParameter("id", id);
        return (User) query.getSingleResult();
    }

    @Override
    public void save(User user) {
        if (user.getId() == 0)
            entityManager.persist(user);
        else
            entityManager.merge(user);
    }
}

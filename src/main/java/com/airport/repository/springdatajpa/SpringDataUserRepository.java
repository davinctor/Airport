package com.airport.repository.springdatajpa;

import com.airport.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


public interface SpringDataUserRepository extends PagingAndSortingRepository<User, Integer> {

    @Query("SELECT DISTINCT user FROM User user  WHERE user.login LIKE :login")
    public User findByFullLogin(@Param("login") String login);

    @Query("SELECT user FROM User user WHERE user.login LIKE CONCAT('%',:login,'%')")
    public Page<User> findUsersByLogin(@Param("login") String login, Pageable pageable);

    @Query("SELECT user FROM User user JOIN FETCH user.staff WHERE user.id = :id")
    public User findByIdWithStaff(@Param("id") int id);

    /**
     * Spring data automatically parse suffix of method name and use arguments for process request to db
     */
    public Page<User> findUsersByRoleOfUser(String roleOfUser, Pageable pageable);

    @Query("SELECT user FROM User user WHERE user.login LIKE CONCAT('%',:login,'%') AND user.roleOfUser LIKE :roleOfUser")
    public Page<User> findUsersByRoleOfUserAndLogin(@Param("roleOfUser") String roleOfUser,
                                                    @Param("login") String login, Pageable pageable);

    @Modifying
    @Query("DELETE FROM User user WHERE user.id = :id")
    public void deleteUserById(@Param("id") int id);
}

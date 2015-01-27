package com.airport.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "LOGIN")
    @Size(min = 3, max = 10,
            message = "Неверный логин")
    private String login;

    @Basic
    @Column(name = "PASSWORD")
    @Size(min = 3, max = 20,
            message = "Неверный пароль")
    private String password;

    @Basic
    @Column(name = "ROLE_OF_USER")
    private String roleOfUser;

    @OneToOne
    @JoinColumn(name = "STAFF_ID")
    private Staff staff;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleOfUser() {
        return roleOfUser;
    }

    public void setRoleOfUser(String roleOfUser) {
        this.roleOfUser = roleOfUser;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String toString() {
        return id + " " + login + " " + password;
    }
}

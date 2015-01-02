package com.airport.model;

import javax.persistence.*;

/**
 * Created by Vic on 01.01.2015.
 */
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Basic
    @Column(name = "STAFF_ID")
    private int staffId;
    @Basic
    @Column(name = "LOGIN")
    private String login;
    @Basic
    @Column(name = "PASSWORD")
    private String password;
    @Basic
    @Column(name = "ADMIN")
    private boolean isAdmin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
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

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String toString() {
        return id + " " + login + " " + password;
    }
}

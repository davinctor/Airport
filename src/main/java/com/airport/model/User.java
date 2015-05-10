package com.airport.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "login")
    @Size(min = 3, max = 10,
            message = "Длина от 3 до 10 символов")
    @NotNull(message = "Пароль не может быть пустым")
    private String login;

    @Basic
    @Column(name = "password")
    @NotNull(message = "Пароль не может быть пустым")
    @Size(min = 3, max = 20,
            message = "Длина от 3 до 10 символов")
    private String password;

    @Basic
    @Column(name = "role_of_user")
    @NotNull
    private String roleOfUser;

    @OneToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    private final static int STRENGTH = 12;

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
        this.password = new BCryptPasswordEncoder(STRENGTH).encode(password);
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

package com.airport.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "STAFF")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "SURNAME")
    @NotNull(message = "Фамилия не может быть пустая")
    @Size(min = 1, max = 25,
            message = "Фамилия может содержать от 1 до 25 символов")
    private String surname;

    @Basic
    @Column(name = "FIRSTNAME")
    @NotNull(message = "Имя не может быть пустым")
    @Size(min = 1, max = 25,
            message = "Имя может содержать от 1 до 25 символов")
    private String firstname;

    @Basic
    @Column(name = "PATRONYMIC")
    @NotNull(message = "Отчество не может быть пустым")
    @Size(min = 1, max = 25,
            message = "Отчество может содержать от 1 до 25 символов")
    private String patronymic;

    @Basic
    @Column(name = "ADDRESS")
    @NotNull(message = "Адрес не может быть пустым")
    @Size(min = 10, max = 100,
            message = "Адрес может содержать от 10 до 100 символов")
    private String address;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "STAFF_PHONES",
            joinColumns = @JoinColumn(name = "STAFF_ID"),
            inverseJoinColumns = @JoinColumn(name = "PHONE_ID"))
    private Collection<Phone> phones;

    public Staff() {
        phones = new ArrayList<Phone>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Collection<Phone> phones) {
        this.phones = phones;
    }

    public String toString() {
        return id + " " + surname + " " + firstname;
    }

}

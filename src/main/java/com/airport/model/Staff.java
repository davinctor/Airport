package com.airport.model;


import javax.persistence.*;
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
    private String surname;
    @Basic
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Basic
    @Column(name = "PATRONYMIC")
    private String patronymic;
    @Basic
    @Column(name = "ADDRESS")
    private String address;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

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

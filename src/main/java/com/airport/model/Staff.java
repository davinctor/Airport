package com.airport.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "surname")
    @NotNull(message = "Фамилия не может быть пустая")
    @Size(min = 1, max = 25,
            message = "Фамилия может содержать от 1 до 25 символов")
    private String surname;

    @Basic
    @Column(name = "firstname")
    @NotNull(message = "Имя не может быть пустым")
    @Size(min = 1, max = 25,
            message = "Имя может содержать от 1 до 25 символов")
    private String firstname;

    @Basic
    @Column(name = "patronymic")
    @NotNull(message = "Отчество не может быть пустым")
    @Size(min = 1, max = 25,
            message = "Отчество может содержать от 1 до 25 символов")
    private String patronymic;

    @Basic
    @Column(name = "address")
    @NotNull(message = "Адрес не может быть пустым")
    @Size(min = 10, max = 100,
            message = "Адрес может содержать от 10 до 100 символов")
    private String address;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY)
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
        return id + "";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Staff))
            return false;
        Staff staff = (Staff) obj;
        if (this.id != staff.getId())
            return false;
        if (!this.surname.equals(staff.getSurname()))
            return false;
        if (!this.firstname.equals(staff.getFirstname()))
            return false;
        if (!this.patronymic.equals(staff.getPatronymic()))
            return false;
        if (!this.address.equals(staff.getAddress()))
            return false;
        return true;
    }
}

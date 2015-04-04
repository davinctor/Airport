package com.airport.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "phones")
public class Phone {

    public static final int MIN_SIZE = 3;
    public static final int MAX_SIZE = 18;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "phone_num")
    private String phoneNumber;

    @ManyToOne
    @JoinTable(name = "staff_phones",
            joinColumns = @JoinColumn(name = "phone_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id"))
    private Staff staff;

    public Phone() {
        this(0, "", new Staff());
    }

    public Phone(int id, String phoneNumber) {
        this(id, phoneNumber, new Staff());
    }

    public Phone(String phoneNumber, Staff staff) {
        this(0, phoneNumber, staff);
    }

    public Phone(int id, String phoneNumber, Staff staff) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.staff = staff;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @Override
    public boolean equals(Object obj) {
        Phone phone = (Phone) obj;
        if (this.id != phone.getId())
            return false;
        if (!this.phoneNumber.equals(phone.getPhoneNumber()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return id + " " + phoneNumber;
    }
}
package com.airport.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "phones")
public class Phone {
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
        staff = new Staff();
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
}
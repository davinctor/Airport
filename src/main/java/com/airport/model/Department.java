package com.airport.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "DEPARTMENTS")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "PHONE_NUM")
    private String phoneNum;
    @Basic
    @Column(name = "SCHEDULE_FROM")
    @Temporal(value = TemporalType.TIME)
    private Date scheduleFrom;
    @Basic
    @Column(name = "SCHEDULE_TO")
    @Temporal(value = TemporalType.TIME)
    private Date scheduleTo;
    @Basic
    @Column(name = "BREAK_FROM")
    @Temporal(value = TemporalType.TIME)
    private Date breakFrom;
    @Basic
    @Column(name = "BREAK_TO")
    @Temporal(value = TemporalType.TIME)
    private Date breakTo;

    @OneToMany(mappedBy = "department")
    private Collection<Staff> staffCollection;

    public Department() {
        super();
        staffCollection = new ArrayList<Staff>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Date getScheduleFrom() {
        return scheduleFrom;
    }

    public void setScheduleFrom(Date scheduleFrom) {
        this.scheduleFrom = scheduleFrom;
    }

    public Date getScheduleTo() {
        return scheduleTo;
    }

    public void setScheduleTo(Date scheduleTo) {
        this.scheduleTo = scheduleTo;
    }

    public Date getBreakFrom() {
        return breakFrom;
    }

    public void setBreakFrom(Date breakFrom) {
        this.breakFrom = breakFrom;
    }

    public Date getBreakTo() {
        return breakTo;
    }

    public void setBreakTo(Date breakTo) {
        this.breakTo = breakTo;
    }

    public Collection<Staff> getStaffCollection() {
        return staffCollection;
    }

    public void setStaffCollection(Collection<Staff> staffCollection) {
        this.staffCollection = staffCollection;
    }
}

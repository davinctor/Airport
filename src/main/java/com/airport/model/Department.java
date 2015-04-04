package com.airport.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "name")
    @NotNull(message = "Укажите название отдела")
    private String name;

    @Basic
    @Column(name = "phone_num")
    @NotNull(message = "Укажите номер телефона отдела")
    private String phoneNum;

    @Basic
    @Column(name = "schedule_from")
    @Temporal(value = TemporalType.TIME)
    private Date scheduleFrom;

    @Basic
    @Column(name = "schedule_to")
    @Temporal(value = TemporalType.TIME)
    private Date scheduleTo;

    @Basic
    @Column(name = "break_from")
    @Temporal(value = TemporalType.TIME)
    private Date breakFrom;

    @Basic
    @Column(name = "break_to")
    @Temporal(value = TemporalType.TIME)
    private Date breakTo;

    @OneToMany(mappedBy = "department")
    private Collection<Staff> staffs;

    public Department() {
        this(0, "");
    }

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
        this.staffs = new ArrayList<Staff>();
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

    public Collection<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(Collection<Staff> staffs) {
        this.staffs = staffs;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
}

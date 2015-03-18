package com.airport.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "Укажите название отдела")
    private String name;
    @Basic
    @Column(name = "PHONE_NUM")
    @NotNull(message = "Укажите номер телефона отдела")
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
    private Collection<Staff> staffs;

    public Department() {
        super();
        staffs = new ArrayList<Staff>();
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
}

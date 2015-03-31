package com.airport.web.editors;

import com.airport.model.Staff;
import com.airport.service.AirportService;

import java.beans.PropertyEditorSupport;

public class StaffEditor extends PropertyEditorSupport {

    private final AirportService airportService;

    public StaffEditor(AirportService airportService) {
        this.airportService = airportService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Staff staff = airportService.findStaffById(Integer.valueOf(text));
        setValue(staff);
    }
}

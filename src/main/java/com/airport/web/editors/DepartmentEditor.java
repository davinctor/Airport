package com.airport.web.editors;


import com.airport.model.Department;

import java.beans.PropertyEditorSupport;

/**
 * Use for save current department of edited staff between page reloads
 */
public class DepartmentEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] args = text.split(" ");
        setValue(new Department(Integer.parseInt(args[0]), args[1]));
    }

}

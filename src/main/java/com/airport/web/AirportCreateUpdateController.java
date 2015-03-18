package com.airport.web;

import com.airport.model.Department;
import com.airport.model.Phone;
import com.airport.model.Staff;
import com.airport.model.User;
import com.airport.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.lang.ref.ReferenceQueue;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class AirportCreateUpdateController {

    private SimpleDateFormat sdf;
    private final String TIME_FORMAT = "HH:mm";

    @Autowired
    private AirportService airportService;

    public AirportCreateUpdateController() {
        this.sdf = new SimpleDateFormat();
    }

    @RequestMapping(value = "/department/edit/{id}", method = RequestMethod.GET)
    public String showUpdateDepartmentForm(@PathVariable int id, Model model, Principal principal,
                                           RedirectAttributes redirectAttributes) {
        Department department = airportService.findDepartmentById(id);
        if (department == null) {
            redirectAttributes.addFlashAttribute("departmentNotFound",
                    "Отдел с кодом " + id + " не найден.");
            return "redirect:/departments/";
        }
        model.addAttribute("department", department);
        model.addAttribute("curUser", getCurrentUser(principal.getName()));
        this.sdf.applyPattern(TIME_FORMAT);
        model.addAttribute("scheduleFrom", this.sdf.format(department.getScheduleFrom()));
        model.addAttribute("scheduleTo", this.sdf.format(department.getScheduleTo()));
        model.addAttribute("breakFrom", this.sdf.format(department.getBreakFrom()));
        model.addAttribute("breakTo", sdf.format(department.getBreakTo()));
        return "createOrUpdateDepartment";
    }

    @RequestMapping(value = "/department/edit/{id}", method = RequestMethod.POST)
    public String updateDepartment(@Valid @ModelAttribute("department") Department department,
                                   Model model,
                                   BindingResult bindingResult,
                                   Principal principal,
                                   @RequestParam(value = "scheduleFromTime") String scheduleFrom,
                                   @RequestParam(value = "scheduleToTime") String scheduleTo,
                                   @RequestParam(value = "breakFromTime") String breakFrom,
                                   @RequestParam(value = "breakToTime") String breakTo) {
        return addSaveDepartment(department, bindingResult, principal, model,
                scheduleFrom, scheduleTo, breakFrom, breakTo);
    }

    @RequestMapping(value = "department/new", method = RequestMethod.GET)
    public String showNewDepartmentForm(Model model, Principal principal) {
        model.addAttribute("curUser", getCurrentUser(principal.getName()));
        model.addAttribute("department", new Department());
        return "createOrUpdateDepartment";
    }

    @RequestMapping(value = "department/new", method = RequestMethod.POST)
    public String addNewDepartment(@Valid @ModelAttribute("department") Department department,
                                   BindingResult bindingResult,
                                   Principal principal,
                                   Model model,
                                   @RequestParam(value = "scheduleFromTime") String scheduleFrom,
                                   @RequestParam(value = "scheduleToTime") String scheduleTo,
                                   @RequestParam(value = "breakFromTime") String breakFrom,
                                   @RequestParam(value = "breakToTime") String breakTo) {

        return addSaveDepartment(department, bindingResult, principal, model,
                scheduleFrom, scheduleTo, breakFrom, breakTo);

    }

    public String addSaveDepartment(Department department, BindingResult bindingResult, Principal principal,
                                    Model model,
                                    String scheduleFrom, String scheduleTo, String breakFrom, String breakTo) {

        Date scheduleFromD = null, scheduleToD = null,
                breakFromD = null, breakToD = null;
        this.sdf.applyPattern(TIME_FORMAT);

        boolean scheduleFromError = scheduleFrom == null ||
                scheduleFrom.isEmpty() || (scheduleFromD = getTime(scheduleFrom)) == null;

        boolean scheduleToError = scheduleTo == null ||
                scheduleTo.isEmpty() || (scheduleToD = getTime(scheduleTo)) == null;

        boolean breakFromError = breakFrom == null ||
                breakFrom.isEmpty() || (breakFromD = getTime(breakFrom)) == null;

        boolean breakToError = breakTo == null ||
                breakTo.isEmpty() || (breakToD = getTime(breakTo)) == null;

        if (bindingResult.hasErrors()
                || scheduleFromError || scheduleToError
                || breakFromError || breakToError) {
            model.addAttribute("curUser", getCurrentUser(principal.getName()));
            checkTimeErrors(model, scheduleFromError, scheduleToError, breakFromError, breakToError);
            for (ObjectError error : bindingResult.getAllErrors())
                outputError(error.toString());
            return "createOrUpdateDepartment";
        }

        department.setScheduleFrom(scheduleFromD);
        department.setScheduleTo(scheduleToD);
        department.setBreakFrom(breakFromD);
        department.setBreakTo(breakToD);
        airportService.saveDepartment(department);
        return "redirect:/department/" + department.getId();

    }

    private void checkTimeErrors(Model model, boolean scheduleFromError, boolean scheduleToError,
                                 boolean breakFromError, boolean breakToError) {
        if (scheduleFromError)
            model.addAttribute("scheduleFromError", "Пожалуйста, укажите время");
        if (scheduleToError)
            model.addAttribute("scheduleToError", "Пожалуйста, укажите время");
        if (breakFromError)
            model.addAttribute("breakFromError", "Пожалуйста, укажите время");
        if (breakToError)
            model.addAttribute("breakToError", "Пожалуйста, укажите время");
    }

    private Date getTime(String time) {
        try {
            return this.sdf.parse(time);
        } catch (ParseException e) {
            outputError(e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "staff/new", method = RequestMethod.GET)
    public String showNewStaffForm(Model model, Principal principal) {
        model.addAttribute("curUser", getCurrentUser(principal.getName()));
        model.addAttribute("staff", new Staff());
        List<Department> deps = airportService.getAllDepartments();
        if (deps.isEmpty())
            model.addAttribute("emptyDepartments", "Список отделов пуст.");
        else
            model.addAttribute("departments", deps);
        return "createOrUpdateStaff";
    }

    @RequestMapping(value = "/staff/new", method = RequestMethod.POST)
    public String addNewStaff(@Valid @ModelAttribute("staff") Staff staff,
                              BindingResult bindingResult,
                              Principal principal,
                              Model model,
                              @RequestParam(value = "department-id", required = false, defaultValue = "-1") Integer departmentId,
                              @RequestParam(value = "phone-numbers", required = false, defaultValue = "") String phones) {
        Department department = null;
        boolean departmentError = departmentId == -1 ||
                (department = airportService.findDepartmentById(departmentId)) == null;
        if (bindingResult.hasErrors() || departmentError) {
            //Error first
            if (departmentError)
                model.addAttribute("departmentError", "Отдел не найден");
            // Data
            model.addAttribute("curUser", getCurrentUser(principal.getName()));
            List<Department> deps = airportService.getAllDepartments();
            if (deps.isEmpty())
                model.addAttribute("emptyDepartments", "Список отделов пуст.");
            else
                model.addAttribute("departments", deps);
            model.addAttribute("phones", phones);
            for (ObjectError error : bindingResult.getAllErrors())
                outputError(error.toString());
            return "createOrUpdateStaff";
        }
        staff.setDepartment(department);
        // Saving phone's numbers
        if (!phones.isEmpty()) {
            staff.getPhones().addAll(createPhonesListForStaff(phones, staff));
        }
        airportService.saveStaff(staff);
        return "redirect:/staff/" + staff.getId();
    }

    private List<Phone> createPhonesListForStaff(String phones, Staff staff) {
        String[] phonesNums = phones.split("\n");
        List<Phone> phonesList = new ArrayList<Phone>(phonesNums.length);
        Phone phone;
        for (String phoneNum : phonesNums) {
            phone = new Phone();
            phone.setPhoneNumber(phoneNum);
            phone.getStaffs().add(staff);
            phonesList.add(phone);
            airportService.savePhone(phone);
        }

        return phonesList;
    }

    @RequestMapping(value = "user/new", method = RequestMethod.GET)
    public String showNewUserForm(Model model, Principal principal) {
        model.addAttribute("curUser", getCurrentUser(principal.getName()));
        model.addAttribute("user", new User());
        checkForUnregisteredStaff(model);
        return "createOrUpdateUser";
    }

    @RequestMapping(value = "/user/new", method = RequestMethod.POST)
    public String addNewUser(@Valid @ModelAttribute("user") User user,
                             BindingResult bindingResult,
                             Principal principal, Model model,
                             @RequestParam(value = "staff-id", required = false, defaultValue = "-1") Integer staffId,
                             @RequestParam(value = "re-password", required = false, defaultValue = "") String rePassword) {
        Staff staff = null;
        boolean rePassError = rePassword.isEmpty()
                || !rePassword.equals(user.getPassword());
        boolean staffError = staffId == -1 ||
                (staff = airportService.findStaffById(staffId)) == null;
        boolean usernameAlreadyExists =
                airportService.findUserByFullLogin(user.getLogin().toLowerCase()) != null;
        if (bindingResult.hasErrors()
                || usernameAlreadyExists
                || rePassError
                || staffError) {
            model.addAttribute("curUser", getCurrentUser(principal.getName()));
            checkForUnregisteredStaff(model);
            if (usernameAlreadyExists)
                model.addAttribute("usernameExists", "Имя пользователя занято.");
            if (rePassError)
                model.addAttribute("rePassError", "Пароли не совпадают!");
            if (staffError)
                model.addAttribute("staffError", "Сотрудник не найден!");
            return "createOrUpdateUser";
        }
        user.setLogin(user.getLogin().toLowerCase());
        user.setStaff(staff);
        staff.setUser(user);
        airportService.saveUser(user);
        airportService.saveStaff(staff);
        return "redirect:/user/" + user.getId();
    }

    private User getCurrentUser(String login) {
        return airportService.findUserByLogin(login);
    }

    private void checkForUnregisteredStaff(Model model) {
        List<Department> deps =
                airportService.getDepartmentsWithInitUnregisteredStaff();
        if (deps.isEmpty())
            model.addAttribute("emptyDepartments", "У всех сотрудников есть профиль пользователя.");
        else
            model.addAttribute("departments", deps);
    }

    private void outputError(String error) {
        Logger.getLogger(AirportCreateUpdateController.class.getName())
                .log(Level.WARNING, error);
    }
}

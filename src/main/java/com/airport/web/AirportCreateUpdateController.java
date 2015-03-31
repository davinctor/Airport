package com.airport.web;

import com.airport.model.Department;
import com.airport.model.Phone;
import com.airport.model.Staff;
import com.airport.model.User;
import com.airport.service.AirportService;
import com.airport.util.ConsoleLog;
import com.airport.web.editors.StaffEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@SessionAttributes("curUser")
public class AirportCreateUpdateController {

    private SimpleDateFormat sdf;
    private final String TIME_FORMAT = "HH:mm";

    @Autowired
    private AirportService airportService;

    public AirportCreateUpdateController() {
        this.sdf = new SimpleDateFormat();
    }

    @InitBinder
    protected void initBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Staff.class, new StaffEditor(airportService));
    }

    /**
     * ******************* DEPARTMENT *****************************
     */

    @RequestMapping(value = "/department/edit/{id}", method = RequestMethod.GET)
    public String showUpdateDepartmentForm(@PathVariable("id") int id, Model model,
                                           RedirectAttributes redirectAttributes) {
        Department department = airportService.findDepartmentById(id);
        if (department == null) {
            redirectAttributes.addFlashAttribute("departmentNotFound",
                    "Отдел с кодом " + id + " не найден.");
            return "redirect:/departments/";
        }
        model.addAttribute("department", department);
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
                                   @RequestParam(value = "scheduleFromTime") String scheduleFrom,
                                   @RequestParam(value = "scheduleToTime") String scheduleTo,
                                   @RequestParam(value = "breakFromTime") String breakFrom,
                                   @RequestParam(value = "breakToTime") String breakTo) {
        return addSaveDepartment(department, bindingResult, model,
                scheduleFrom, scheduleTo, breakFrom, breakTo);
    }

    @RequestMapping(value = "department/new", method = RequestMethod.GET)
    public String showNewDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "createOrUpdateDepartment";
    }

    @RequestMapping(value = "department/new", method = RequestMethod.POST)
    public String addNewDepartment(@Valid @ModelAttribute("department") Department department,
                                   BindingResult bindingResult,
                                   Model model,
                                   @RequestParam(value = "scheduleFromTime") String scheduleFrom,
                                   @RequestParam(value = "scheduleToTime") String scheduleTo,
                                   @RequestParam(value = "breakFromTime") String breakFrom,
                                   @RequestParam(value = "breakToTime") String breakTo) {

        return addSaveDepartment(department, bindingResult, model,
                scheduleFrom, scheduleTo, breakFrom, breakTo);

    }

    public String addSaveDepartment(Department department, BindingResult bindingResult, Model model,
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
            checkTimeErrors(model, scheduleFromError, scheduleToError, breakFromError, breakToError);
            for (ObjectError error : bindingResult.getAllErrors())
                ConsoleLog.outputError(error.toString());
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
            ConsoleLog.outputError(e.getMessage());
            return null;
        }
    }

    /**
     * ******************* STAFF *****************************
     */

    @RequestMapping(value = "staff/edit/{id}", method = RequestMethod.GET)
    public String showStaffUpdateForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Staff staff = airportService.findStaffById(id);
        if (staff == null) {
            redirectAttributes.addAttribute("staffNotFound",
                    "Cотрудник с id " + id + " не найден.");
            return "redirect:/staffs/";
        }
        model.addAttribute("staff", staff);
        getDepartments(model);
        return "createOrUpdateStaff";
    }

    @RequestMapping(value = "staff/new", method = RequestMethod.GET)
    public String showNewStaffForm(Model model) {
        model.addAttribute("staff", new Staff());
        getDepartments(model);
        return "createOrUpdateStaff";
    }

    @RequestMapping(value = "/staff/new", method = RequestMethod.POST)
    public String addNewStaff(@Valid @ModelAttribute("staff") Staff staff,
                              BindingResult bindingResult,
                              Model model,
                              @RequestParam(value = "department-id", required = false, defaultValue = "-1")
                              Integer departmentId,
                              @RequestParam(value = "phone-numbers", required = false, defaultValue = "")
                              String phones) {
        Department department = null;
        boolean departmentError = departmentId == -1 ||
                (department = airportService.findDepartmentById(departmentId)) == null;
        if (bindingResult.hasErrors() || departmentError) {
            //Error first
            if (departmentError)
                model.addAttribute("departmentError", "Отдел не найден");
            // Data
            getDepartments(model);
            model.addAttribute("phones", phones);
            for (ObjectError error : bindingResult.getAllErrors())
                ConsoleLog.outputError(error.toString());
            return "createOrUpdateStaff";
        }
        staff.setDepartment(department);
        // Saving phone's numbers
        airportService.saveStaff(staff);
        if (!phones.isEmpty()) {
            staff.getPhones().addAll(createPhonesListForStaff(phones, staff));
        }
        return "redirect:/staff/" + staff.getId();
    }

    private void getDepartments(Model model) {
        List<Department> deps = airportService.getAllDepartments();
        if (deps.isEmpty())
            model.addAttribute("emptyDepartments", "Список отделов пуст.");
        else
            model.addAttribute("departments", deps);
    }

    private List<Phone> createPhonesListForStaff(String phones, Staff staff) {
        String[] phonesNums = phones.split("\n");
        List<Phone> phonesList = new ArrayList<Phone>(phonesNums.length);
        Phone phone;
        for (String phoneNum : phonesNums) {
            phone = new Phone();
            phone.setPhoneNumber(phoneNum);
            phone.setStaff(staff);
            phonesList.add(phone);
            airportService.savePhone(phone);
        }

        return phonesList;
    }


    /**
     * ******************* USER *****************************
     */


    @RequestMapping(value = "/user/edit/{id}", method = RequestMethod.GET)
    public String showUpdateUserForm(@PathVariable int id, Model model,
                                     RedirectAttributes redirectAttributes) {
        User user = airportService.findUserById(id);
        if (user == null) {
            redirectAttributes.addAttribute("userNotFound",
                    "Пользователь с id " + id + " не найден.");
            return "redirect:/users/";
        }
        model.addAttribute("user", user);
        getStaffsWithNullUser(model, id);
        return "createOrUpdateUser";
    }

    @RequestMapping(value = "/user/edit/{id}", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User user,
                             @PathVariable Integer id,
                             Model model, BindingResult bindingResult,
                             @RequestParam(value = "staff-id", required = false, defaultValue = "-1")
                             Integer staffId,
                             @RequestParam(value = "re-password", required = false, defaultValue = "")
                             String rePassword) {
        return addSaveUser(user, bindingResult, model, staffId, rePassword,
                false, id);
    }

    @RequestMapping(value = "user/new", method = RequestMethod.GET)
    public String showNewUserForm(Model model) {
        model.addAttribute("user", new User());
        getStaffsWithNullUser(model, AirportService.WITHOUT_CUR_STAFF);
        return "createOrUpdateUser";
    }

    @RequestMapping(value = "/user/new", method = RequestMethod.POST)
    public String addNewUser(@Valid @ModelAttribute("user") User user,
                             BindingResult bindingResult,
                             Model model,
                             @RequestParam(value = "staff-id", required = false, defaultValue = "-1")
                             Integer staffId,
                             @RequestParam(value = "re-password", required = false, defaultValue = "")
                             String rePassword) {
        return addSaveUser(user, bindingResult, model, staffId, rePassword,
                true, AirportService.WITHOUT_CUR_STAFF);
    }

    private String addSaveUser(User user, BindingResult bindingResult, Model model,
                               int staffId, String rePassword, boolean create, int includeId) {
        Staff staff = staffId != -1 ? airportService.findStaffById(staffId) : null;
        if (checkUserFormErrors(rePassword, user, staff, create, model, bindingResult, includeId))
            return "createOrUpdateUser";
        if (create)
            user.setLogin(user.getLogin().toLowerCase());
        else {
            Staff old = airportService.findStaffByUserId(user.getId());
            if (!old.equals(staff)) {
                old.setUser(null);
                airportService.saveStaff(old);
            }
        }
        user.setStaff(staff);
        staff.setUser(user);
        airportService.saveUser(user);
        airportService.saveStaff(staff);
        User curUser;
        if ((curUser = (User) model.asMap().get("curUser")).getId() == includeId &&
                !user.getPassword().equals(curUser.getPassword()))
            SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        return "redirect:/user/" + user.getId();
    }

    private boolean checkUserFormErrors(String rePassword, User user, Staff staff, boolean create,
                                        Model model, BindingResult bindingResult, int includeId) {
        boolean rePassError = !rePassword.equals(user.getPassword());
        boolean staffError = staff == null ||
                (staff.getUser() != null && staff.getUser().getId() != user.getId());
        boolean usernameAlreadyExists =
                create && airportService.findUserByFullLogin(user.getLogin().toLowerCase()) != null;
        if (bindingResult.hasErrors()
                || usernameAlreadyExists
                || rePassError
                || staffError) {
            ConsoleLog.outputError(bindingResult.toString());
            getStaffsWithNullUser(model, includeId);
            if (usernameAlreadyExists)
                model.addAttribute("usernameExists", "Имя пользователя занято.");
            if (rePassError)
                model.addAttribute("rePassError", "Пароли не совпадают!");
            if (staffError)
                model.addAttribute("staffError", "Сотрудник не найден!");
            return true;
        }
        return false;
    }


    private void getStaffsWithNullUser(Model model, int curUserStaffId) {
        List<Department> deps =
                airportService.getDepartmentsWithStaffNullUser(curUserStaffId);
        if (deps.isEmpty())
            model.addAttribute("emptyDepartments", "У всех сотрудников есть профиль пользователя.");
        else
            model.addAttribute("departments", deps);
    }

    @ModelAttribute("curUser")
    public User getCurrentUser(Principal principal) {
        return airportService.findUserByFullLogin(principal.getName());
    }
}

package com.airport.web;

import com.airport.model.Department;
import com.airport.model.Staff;
import com.airport.model.User;
import com.airport.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;

@Controller
@SessionAttributes("curUser")
public class AirportController {

    private AirportService airportService;

    @Autowired
    @Qualifier("listServicesMainPage")
    private ArrayList listServicesMainPage;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }


    @RequestMapping(value = "/reservation/find")
    public String showReservationForm() {
        return "reservationFind";
    }

    @RequestMapping(value = "departments", method = RequestMethod.GET)
    public String showDepartmentsList(
            @PageableDefault(page = 0, value = 10) Pageable pageable,
            Model model,
            @ModelAttribute("deleteDepartmentSuccess") String deleteSuccess,
            @ModelAttribute("notFoundDepartment") String notFoundDepartment) {
        model.addAttribute("departments",
                airportService.getDepartments(pageable).getContent());
        model.addAttribute("deleteDepartmentSuccess", deleteSuccess);
        model.addAttribute("notFoundDepartment", notFoundDepartment);
        return "departments";
    }

    @RequestMapping(value = "/department/{departmentId}", method = RequestMethod.GET)
    public String showDepartment(@PathVariable("departmentId") int departmentId, Model model,
                                 RedirectAttributes redirectAttributes) {
        Department department = airportService.findDepartmentById(departmentId);
        if (department == null) {
            redirectAttributes.addFlashAttribute("departmentNotFound",
                    "Отдел не найден.");
            return "redirect:/departments";
        }
        model.addAttribute("department", department);
        return "department";
    }

    @RequestMapping(value = "/staff/{staffId}", method = RequestMethod.GET)
    public String showStaff(@PathVariable("staffId") int staffId, Model model, RedirectAttributes redirectAttributes) {
        Staff staff = airportService.findStaffByIdWithPhones(staffId);
        if (staff == null) {
            redirectAttributes.addFlashAttribute("staffNotFound", "Сотрудник не найден");
            return "redirect:/staffs";
        }
        model.addAttribute("staff", staff);
        return "staff";
    }

    @RequestMapping(value = "/staffs", method = RequestMethod.GET)
    public String showStaffsList(
            @PageableDefault(page = 0, value = 10) Pageable pageable,
            Model model,
            @ModelAttribute("staffDeleteSuccess") String deleteSuccess,
            @ModelAttribute("staffNotFound") String notFound) {
        model.addAttribute("staffs",
                airportService.getStaffs(pageable).getContent());
        model.addAttribute("staffDeleteSuccess", deleteSuccess);
        model.addAttribute("departmentNotFound", notFound);
        return "staffs";
    }

    /**
     * Custom handler for displaying user
     *
     * @param userId the ID of user to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public String showUser(@PathVariable("userId") int userId, Model model,
                           @ModelAttribute("restrictDeleteStaff") String restrictDelete,
                           RedirectAttributes redirectAttributes) {
        User user = airportService.findUserById(userId);
        if (user == null) {
            redirectAttributes.addFlashAttribute("userNotFound", "Пользователь не найден.");
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        model.addAttribute("restrictDeleteStaff", restrictDelete);
        return "user";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showUsersList(@PageableDefault(page = 0, value = 10) Pageable pageable,
                                Model model,
                                @ModelAttribute("userDeleteSuccess") String deleteSuccess,
                                @ModelAttribute("userNotFound") String notFound) {
        model.addAttribute("users", airportService.getUsers(pageable).getContent());
        model.addAttribute("deleteUserSuccess", deleteSuccess);
        model.addAttribute("userNotFound", notFound);
        return "users";
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("listServices", listServicesMainPage);
        return "main";
    }

    @ModelAttribute("curUser")
    public User getCurrentUser(Principal principal) {
        return airportService.findUserByFullLogin(principal.getName());
    }
}

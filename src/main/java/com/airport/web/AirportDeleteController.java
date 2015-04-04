package com.airport.web;

import com.airport.model.Staff;
import com.airport.model.User;
import com.airport.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.security.Principal;

@Controller
public class AirportDeleteController {
    @Autowired
    private AirportService airportService;

    @RequestMapping(value = "staff/delete/{staffId}", method = RequestMethod.GET)
    public ModelAndView deleteStaff(@PathVariable("staffId") int staffId,
                                    RedirectAttributes redirectAttributes) {
        if (airportService.findStaffById(staffId) != null) {
            Staff staff = airportService.findStaffById(staffId);
            if (staff.getUser() != null) {
                redirectAttributes.addFlashAttribute("restrictDeleteStaff",
                        "Сотрудника " + staff.getSurname() + " " + staff.getFirstname() +
                                " удалить нельзя, т.к. он привязан этому пользователя." +
                                " Сперва нужно удалить пользователя.");
                return new ModelAndView("redirect:/user/" + staff.getUser().getId());
            }
            airportService.deleteStaffById(staffId);
            redirectAttributes.addFlashAttribute("staffDeleteSuccess",
                    "Сотрудник " + staff.getSurname() + " " + staff.getFirstname() +
                            " успешно удален.");
        } else
            redirectAttributes.addFlashAttribute("staffNotFound", "Сотрудник не существует.");
        return new ModelAndView("redirect:/staffs");
    }

    @RequestMapping(value = "user/delete/{userId}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("userId") int userId, Principal principal,
                             RedirectAttributes redirectAttributes) {
        User user = airportService.findUserById(userId);
        if (user != null) {
            if (user.getLogin().equals(principal.getName()))
                SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
            user.getStaff().setUser(null);
            airportService.saveStaff(user.getStaff());
            airportService.deleteUserById(userId);
            redirectAttributes.addFlashAttribute("userDeleteSuccess",
                    "Пользователь " + user.getLogin() + " успешно удален.");
        } else
            redirectAttributes.addFlashAttribute("userNotFound", "Пользователь не существует.");
        return "redirect:/users/";
    }

    @RequestMapping(value = "department/delete/{departmentId}", method = RequestMethod.GET)
    public String deleteDepartment(@PathVariable("departmentId") int departmentId,
                                   @RequestParam(value = "departmentName", required = false) String departmentName,
                                   @RequestParam(value = "confirm", required = false) String confirmDelete,
                                   RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
        if (airportService.findDepartmentById(departmentId) != null) {
            if (confirmDelete == null) {
                int staffNumber = airportService.findDepartmentByIdWithStaffs(departmentId).getStaffs().size();
                if (staffNumber > 0) {
                    redirectAttributes.addFlashAttribute("staffNumber", staffNumber);
                    return "redirect:/department/" + departmentId;
                }
            }
            airportService.deleteStaffsByDepartmentId(departmentId);
            airportService.deleteDepartmentById(departmentId);
            if (departmentName != null) {
                redirectAttributes.addFlashAttribute("deleteDepartmentSuccess",
                        "Отдел " + departmentName + " успешно удален.");
            } else
                redirectAttributes.addFlashAttribute("deleteDepartmentSuccess", "Отдел успешно удален.");
        } else
            redirectAttributes.addFlashAttribute("departmentNotFound", "Отдел не существует.");
        return "redirect:/departments/";
    }
}

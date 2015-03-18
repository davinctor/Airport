package com.airport.web;

import com.airport.model.Staff;
import com.airport.model.User;
import com.airport.security.SecurityContextAccessor;
import com.airport.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class AirportController {

    private AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @Autowired
    private SecurityContextAccessor securityContextAccessor;
    @Autowired
    @Qualifier("defaultTargetUrl")
    private String defaultTargetUrl;

    @Autowired
    @Qualifier("listServicesMainPage")
    private ArrayList listServicesMainPage;

    @RequestMapping(value = "/reservation/find")
    public String showReservationForm(Model model, Principal principal) {
        model.addAttribute("curUser", getCurrentUser(principal.getName()));
        return "reservationFind";
    }

    @RequestMapping(value = "departments", method = RequestMethod.GET)
    public String showDepartmentsList(
            @PageableDefault(page = 0, value = 10) Pageable pageable,
            Model model, Principal principal,
            @ModelAttribute("deleteDepartmentSuccess") String deleteSuccess) {
        model.addAttribute("curUser", getCurrentUser(principal.getName()));
        model.addAttribute("departments",
                airportService.getDepartments(pageable).getContent());
        model.addAttribute("deleteDepartmentSuccess", deleteSuccess);
        return "departments";
    }

    @RequestMapping(value = "/department/{departmentId}", method = RequestMethod.GET)
    public String showDepartment(@PathVariable("departmentId") int departmentId,
                                 Model model, Principal principal) {
        model.addAttribute("curUser", getCurrentUser(principal.getName()));
        model.addAttribute("department", airportService.findDepartmentById(departmentId));
        return "department";
    }

    @RequestMapping(value = "/staff/{staffId}", method = RequestMethod.GET)
    public String showStaff(@PathVariable("staffId") int staffId, Model model,
                            Principal principal) {
        model.addAttribute("curUser", getCurrentUser(principal.getName()));
        model.addAttribute("staff", airportService.findStaffByIdWithPhones(staffId));
        return "staff";
    }

    @RequestMapping(value = "/staffs", method = RequestMethod.GET)
    public String showStaffsList(
            @PageableDefault(page = 0, value = 10) Pageable pageable,
            Model model, Principal principal,
            @ModelAttribute("staffDeleteSuccess") String deleteSuccess) {
        model.addAttribute("curUser", getCurrentUser(principal.getName()));
        model.addAttribute("staffs",
                airportService.getStaffs(pageable).getContent());
        model.addAttribute("staffDeleteSuccess", deleteSuccess);
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
                           Principal principal,
                           @ModelAttribute("restrictDeleteStaff") String restrictDelete) {
        model.addAttribute("curUser", getCurrentUser(principal.getName()));
        model.addAttribute("user", airportService.findUserByIdWithPhones(userId));
        model.addAttribute("restrictDeleteStaff", restrictDelete);
        return "user";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showUsersList(@PageableDefault(page = 0, value = 10) Pageable pageable,
                                Model model, Principal principal,
                                @ModelAttribute("deleteUserSuccess") String deleteSuccess) {
        model.addAttribute("curUser", getCurrentUser(principal.getName()));
        model.addAttribute("users", airportService.getUsers(pageable).getContent());
        model.addAttribute("deleteUserSuccess", deleteSuccess);
        return "users";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model,
                        @RequestParam(value = "fuckyou", required = false) String error,
                        @RequestParam(value = "byebye", required = false) String logout) {
        if (!securityContextAccessor.isCurrentAuthenticationAnonymous())
            return "redirect:" + defaultTargetUrl;

        if (error != null)
            model.addAttribute("error", "Неверный логин или пароль.");

        if (logout != null)
            model.addAttribute("logout", "Вы успешно вышли из своей учетной записи.");

        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model, Principal principal) {
        model.addAttribute("curUser", getCurrentUser(principal.getName()));
        model.addAttribute("listServices", listServicesMainPage);
        return "main";
    }

    public ArrayList getListServicesMainPage() {
        return listServicesMainPage;
    }

    public void setListServicesMainPage(ArrayList listServicesMainPage) {
        this.listServicesMainPage = listServicesMainPage;
    }

    private User getCurrentUser(String login) {
        return airportService.findUserByLogin(login);
    }

    private void outputError(String error) {
        Logger.getLogger(AirportCreateUpdateController.class.getName())
                .log(Level.WARNING, error);
    }
}

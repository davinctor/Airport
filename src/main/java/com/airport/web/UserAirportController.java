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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class UserAirportController {

    private AirportService airportService;

    @Autowired
    public UserAirportController(AirportService airportService) {
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

    @RequestMapping(value = "departments", method = RequestMethod.GET)
    public String showDepartmentsList(
            @PageableDefault(page = 0, value = 2) Pageable pageable,
            Model model, Principal principal) {
        model.addAttribute("curUser", getCurrentUser(principal.getName()));
        model.addAttribute("departments",
                airportService.getDepartments(pageable).getContent());
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
        model.addAttribute("staff", airportService.findStaffById(staffId));
        return "staff";
    }

    @RequestMapping(value = "/staffs", method = RequestMethod.GET)
    public String showStaffsList(
            @PageableDefault(page = 0, value = 2) Pageable pageable,
            Model model, Principal principal) {
        model.addAttribute("curUser", getCurrentUser(principal.getName()));
        model.addAttribute("staffs",
                airportService.getStaffs(pageable).getContent());
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
                           Principal principal) {
        model.addAttribute("curUser", getCurrentUser(principal.getName()));
        model.addAttribute("user", airportService.findUserById(userId));
        return "user";
    }

    @Transactional
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showUsersList(@PageableDefault(page = 0, value = 3) Pageable pageable,
                                Model model, Principal principal) {
        model.addAttribute("curUser", getCurrentUser(principal.getName()));
        model.addAttribute("users", airportService.getUsers(pageable).getContent());
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
}

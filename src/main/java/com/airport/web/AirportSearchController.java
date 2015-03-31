package com.airport.web;

import com.airport.model.User;
import com.airport.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
@SessionAttributes("curUser")
public class AirportSearchController {

    private final int DONT_CHOOSE = 1;
    private final int ADMIN = 2;
    private final int USER = 3;

    @Autowired
    private AirportService airportService;

    @RequestMapping(value = "/user/search", method = RequestMethod.GET)
    public String showFindUsersResult(
            @PageableDefault(page = 0, value = 10) Pageable pageable,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "role", required = false) Integer role,
            Model model) {
        model.addAttribute("role", role);
        model.addAttribute("username", username);
        List usersList = null;
        boolean emptyLogin = username == null || username.isEmpty();
        if (!emptyLogin && role != null && role > DONT_CHOOSE) {
            usersList =
                    airportService.findUsersByRoleAndLogin(getRole(role),
                            username, pageable).getContent();
        } else {
            if (role != null) {
                if (role == DONT_CHOOSE)
                    usersList = airportService.getUsers(pageable).getContent();

                if (role == USER || role == ADMIN)
                    usersList = airportService.findUsersByRole(getRole(role), pageable).getContent();
            }
            if (!emptyLogin)
                usersList = airportService.findUsersByLogin(username, pageable).getContent();
        }
        model.addAttribute("users", usersList);
        if (usersList.size() < 1)
            model.addAttribute("searchError",
                    "Поиск не дал результатов... Попробуйте изменить поисковый запрос.");
        return "users";
    }

    private String getRole(int role) {
        switch (role) {
            case ADMIN:
                return "ROLE_ADMIN";
            case USER:
                return "ROLE_USER";
        }
        return "";
    }

    @ModelAttribute("curUser")
    public User getCurrentUser(Principal principal) {
        return airportService.findUserByFullLogin(principal.getName());
    }
}


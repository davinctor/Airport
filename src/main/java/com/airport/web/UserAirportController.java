package com.airport.web;

import com.airport.model.User;
import com.airport.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(types = User.class)
public class UserAirportController {

    private AirportService airportService;

    @Autowired
    public UserAirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    /**
     * Custom handler for displaying user
     *
     * @param userId the ID of user to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public String showUser(@PathVariable("userId") int userId, Model model) {
        model.addAttribute("user", airportService.findUserById(userId));
        return "userDetails";
    }
}

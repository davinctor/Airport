package com.airport.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AirportLoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model,
                        @RequestParam(value = "fuckyou", required = false) String error,
                        @RequestParam(value = "byebye", required = false) String logout) {

        if (error != null)
            model.addAttribute("error", "Неверный логин или пароль.");

        if (logout != null)
            model.addAttribute("logout", "Вы успешно вышли из своей учетной записи.");

        return "login";
    }

}

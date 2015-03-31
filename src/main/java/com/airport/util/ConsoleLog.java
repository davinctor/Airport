package com.airport.util;


import com.airport.web.AirportCreateUpdateController;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleLog {
    public static void outputError(String error) {
        Logger.getLogger(AirportCreateUpdateController.class.getName())
                .log(Level.WARNING, error);
    }
}

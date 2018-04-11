package com.Shubh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnChecker {

    public static Boolean isChargerConnected() {
        try {
            Process proc = Runtime.getRuntime().exec("acpi");

            BufferedReader stdInput = new BufferedReader(
                    new InputStreamReader(proc.getInputStream()));
            BufferedReader stdError = new BufferedReader(
                    new InputStreamReader(proc.getErrorStream()));

            String s;
            while ((s = stdInput.readLine()) != null) {
                if (s.contains("Discharging")) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}

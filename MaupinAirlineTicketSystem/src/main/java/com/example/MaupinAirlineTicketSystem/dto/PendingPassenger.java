package com.example.MaupinAirlineTicketSystem.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class PendingPassenger implements Serializable {

    private String firstName;
    private String lastName;
    private String passport;
    private LocalDate dob;

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPassport() { return passport; }
    public void setPassport(String passport) { this.passport = passport; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }
}
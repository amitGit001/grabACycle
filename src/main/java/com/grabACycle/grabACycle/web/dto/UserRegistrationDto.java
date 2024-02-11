package com.grabACycle.grabACycle.web.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UserRegistrationDto {

    @NotEmpty(message = "First Name can not be blank.")
    @Size(min = 2, message = "First Name should atleast contain 2 characters.")
    private String firstName;

    @NotEmpty(message = "Last Name can not be blank.")
    @Size(min = 2, message = "Last Name should atleast contain 2 characters.")
    private String lastName;

    @Email(message = "Invalid email.", regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    @NotEmpty(message = "Email can not be blank")
    private String email;

    @NotEmpty(message = "Password can not be blank.")
    @Size(min = 3, message = "Password should atleast contain 3 characters.")
    private String password;

    @NotNull(message = "Date of birth must not be null")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;

    public UserRegistrationDto(String firstName, String lastName, String email, String password, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dob=dob;
    }

    public UserRegistrationDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

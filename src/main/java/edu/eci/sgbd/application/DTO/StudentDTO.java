package edu.eci.sgbd.application.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public class StudentDTO {
    @NotBlank(message="name is required")
    private String name;
    @NotBlank(message="email is required")
    private String email;
    @NotNull(message="birthDate is required")
    @Past(message="birthDate must be in the past")
    private LocalDate birthDate;
    @NotNull
    @NotBlank(message="City is required")
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

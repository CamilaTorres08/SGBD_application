package edu.eci.sgbd.application.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SubjectDTO {
    @NotBlank(message="name is required")
    private String name;
    @NotNull(message="credits is required")
    @NotNull
    @Min(value = 1, message = "Credits must be greater than 0")
    private int credits;
    @NotBlank(message="Department is required")
    private String department;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

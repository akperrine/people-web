package com.example.peopleweb.biz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "First Name can not be empty")
    private String firstName;
    @NotEmpty(message = "First Name can not be empty")
    private String lastName;
    @Past(message = "Date of birth must be in the past")
    @NotNull(message = "Date of birth must be specified")
    private LocalDate dob;
    @Email(message = "must be a email")
    @NotEmpty(message = "email must not be empty")
    private String email;
    @DecimalMin(value = "1000", message = "Salary must be at least 1000")
    @NotNull(message = "Salary must be specified")
    private BigDecimal salary;

    private String photoFilename;
}

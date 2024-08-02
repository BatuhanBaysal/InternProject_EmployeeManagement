package com.tutorial.employeemanagementbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Getter
@Setter
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Long salary;
    private Date startDate;
    private Date finishDate;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
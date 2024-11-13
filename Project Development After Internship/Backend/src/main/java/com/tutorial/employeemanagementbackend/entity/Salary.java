package com.tutorial.employeemanagementbackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity // Java Persistence API(JPA) Entity. In a database, this class corresponds to a table.
@Table(name = "employeeSalaries") // The name of the table is set to “employeeSalaries”.
@Data // The Lombok library automatically creates methods @AllArgsConstructor, @NoArgsConstructor, @Getter, @Setter, @ToString, and @EqualsAndHashCode.
public class Salary {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Each time a new Salary object is added, the id field is assigned a unique value by the database that follows the previous value.
    private Long id; // private is used for identification, data protection and controlled access.

    @Column(name ="salaries", nullable = false) // cannot be ignored. @Column(nullable = false) is a constraint at the database level.
    @NotNull // Fields should not be left blank. This is checked by JPA before saving to the database. @NotNull is part of Java's Bean Validation API.
    private Double salaries;

    @Column(name ="startDate", nullable = false)
    @NotNull
    private LocalDate startDate;

    @Column(name ="finishDate") // There is no need to put @Column. It understands that there is a column even if there is no @Column.
    private LocalDate finishDate;
}
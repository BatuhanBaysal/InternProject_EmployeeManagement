package com.tutorial.employeemanagementbackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity // Java Persistence API(JPA) Entity. In a database, this class corresponds to a table.
@Table(name = "employees") // The name of the table is set to “employees”.
@Data // The Lombok library automatically creates methods @AllArgsConstructor, @NoArgsConstructor, @Getter, @Setter, @ToString, and @EqualsAndHashCode.
public class Employee {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Each new Employee object is automatically assigned a unique value in the id field by the database that follows the previous value.
    private Long id; // private is used for identification, data protection and controlled access.

    @Column(name = "firstName", nullable = false) // cannot be ignored. @Column(nullable = false) is a constraint at the database level.
    @NotNull // Fields should not be left blank. This is checked by JPA before saving to the database. @NotNull is part of Java's Bean Validation API.
    private String firstName;

    @Column(name = "lastName", nullable = false)
    @NotNull
    private String lastName;

    @Column(name = "email", nullable = false)
    @NotNull
    private String email;

    @ManyToOne(cascade = CascadeType.ALL) // It ensures that all operations (add, update, delete) on other entities associated with an entity are done automatically.
    @JoinColumn(name = "salary_id") // @JoinColumn defines the relationship between two entities through a specific column in the database and specifies the associated column.
    private Salary salary; // salary field is the foreign key here.
}
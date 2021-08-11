package com.example.tangram.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * class Entity of employee
 */
@Entity
public class Employee {
    @GeneratedValue(
        strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Id
    private Long id;

    /**
     * unique field
     */
    @Column(name = "tz", nullable = false, unique = true)
    private Long tz;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "startWork", nullable = false)
    private LocalDate startWork;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getTz() {
        return tz;
    }

    public void setTz(Long tz) {
        this.tz = tz;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getStartWork() {
        return startWork;
    }

    public void setStartWork(LocalDate startWork) {
        this.startWork = startWork;
    }

    public Employee() {
    }

    public Employee(Long id, String name, String lastName, Long tz, LocalDate birthday, LocalDate startWork) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.tz = tz;
        this.birthday = birthday;
        this.startWork = startWork;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id.equals(employee.id) && tz.equals(employee.tz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tz);
    }

    @Override
    public String toString() {
        return "Employee{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", lastName='" + lastName + '\'' +
            ", tz='" + tz + '\'' +
            ", birthday='" + birthday + '\'' +
            ", startWork='" + startWork + '\'' +
            '}';
    }
}

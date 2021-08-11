package com.example.tangram.repo;

import com.example.tangram.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * repository for {@link Employee}
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByTz(long tz);
}

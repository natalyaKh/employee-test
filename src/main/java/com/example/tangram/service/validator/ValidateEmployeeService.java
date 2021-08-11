package com.example.tangram.service.validator;

import com.example.tangram.model.Employee;

/**
 * servise of validation
 */
public interface ValidateEmployeeService {
    /**
     * method that check if employee exist in DB
     * @param tz - teudat zeut of employee
     * @return {@link Employee} from DB if employee exists or NULL if not
     */
    Employee checkEmployeeByTz(long tz);
}

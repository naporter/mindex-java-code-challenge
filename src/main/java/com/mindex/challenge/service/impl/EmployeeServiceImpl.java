package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Getting employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    /**
     * Gets the number of reports given an employee ID.
     * Complexity O(log(n) * n)
     *
     * @param id - employee ID
     * @return the number of reports for the given
     */
    @Override
    public int getNumberOfReports(String id) {
        LOG.debug("Getting employee reporting structure with id [{}]", id);
        int numberOfReports = 0;
        // O(log(n)) if database is indexed by the employee ID
        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        if (employee.getDirectReports() != null) {
            // O(n) calls once for every report under a single employee
            for (Employee directReport : employee.getDirectReports()) {
                numberOfReports++;
                numberOfReports += getNumberOfReports(directReport.getEmployeeId());
            }
        }

        return numberOfReports;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }
}

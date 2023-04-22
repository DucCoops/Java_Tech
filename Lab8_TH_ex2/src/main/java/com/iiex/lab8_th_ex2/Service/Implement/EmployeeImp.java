package com.iiex.lab8_th_ex2.Service.Implement;



import com.iiex.lab8_th_ex2.Model.Employee;
import com.iiex.lab8_th_ex2.Repository.EmployeeRep;
import com.iiex.lab8_th_ex2.Service.IEmployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Controller
public class EmployeeImp implements IEmployService {
    @Autowired
    private EmployeeRep employeeRep;
    @Override
    public List<Employee> getAll() {
        return employeeRep.findAll();
    }

    @Override
    public Employee getOne(int id) {

        return employeeRep.findById(id).get();
    }

    @Override
    public void delete(int id) {
        employeeRep.delete(getOne(id));
    }

    @Override
    public void add(Employee employee) {
        employeeRep.save(employee);
    }

    @Override
    public void update(Employee employee) {
        employeeRep.save(employee);
    }
}

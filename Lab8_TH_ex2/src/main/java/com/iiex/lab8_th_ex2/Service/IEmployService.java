package com.iiex.lab8_th_ex2.Service;

import com.iiex.lab8_th_ex2.Model.Employee;
import com.iiex.lab8_th_ex2.Service.Implement.EmployeeImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IEmployService {
    public static IEmployService getInstance() {
        return (IEmployService) new EmployeeImp();
    }
    List<Employee> getAll();

    Employee getOne(int id);

    void delete(int id);

    void add(Employee employee);

    void update(Employee employee);
}

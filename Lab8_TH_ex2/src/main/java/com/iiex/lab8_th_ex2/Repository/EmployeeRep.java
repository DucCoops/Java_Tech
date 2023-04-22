package com.iiex.lab8_th_ex2.Repository;


import com.iiex.lab8_th_ex2.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRep extends JpaRepository<Employee,Integer> {

}

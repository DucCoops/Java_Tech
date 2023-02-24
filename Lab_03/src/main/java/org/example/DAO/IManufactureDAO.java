package org.example.DAO;

import org.example.POJO.Manufacture;

import java.util.List;

public interface IManufactureDAO extends Generic<Manufacture>{

    boolean over100emp();
    int sumEmp() ;
    Manufacture lastInUSA();
}

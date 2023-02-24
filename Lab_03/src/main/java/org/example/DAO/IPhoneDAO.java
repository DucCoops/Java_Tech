package org.example.DAO;

import org.example.POJO.Phone;

import java.util.List;

public interface IPhoneDAO extends Generic<Phone>{
    Phone getHighestPrice();
    List<Phone> Sort();
    boolean  Above50Millions(Phone p);
    Phone FirstPhone();
}

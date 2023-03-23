package com.nguyenduc.dao;

import com.nguyenduc.model.Usertable;
import com.nguyenduc.repository.IUser;

import java.util.List;

public class UserDAO extends AbstractDAO<Usertable> implements IUser {
    public UserDAO(Class<Usertable> clazz) {
        super(clazz);
    }

    @Override
    public Usertable isValidUser(Usertable usertable) {
        String hql = "FROM Usertable where email=?1 and pwd=?2";
       List<Usertable> users = this.query(hql,usertable.getEmail(),usertable.getPwd());
       try {
            users.get(0);
            return   users.get(0);
       }catch (Exception e){
            return null;
        }
    }


    @Override
    public List<Usertable> getAll() {
        String hql = "FROM Usertable";
        return this.query(hql);
    }
}

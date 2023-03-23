package com.nguyenduc.repository;

import com.nguyenduc.model.Usertable;

public interface IUser extends genericDAO<Usertable> {
    Usertable isValidUser(Usertable usertable);
}

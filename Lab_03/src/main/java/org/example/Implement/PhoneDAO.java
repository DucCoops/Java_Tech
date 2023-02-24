package org.example.Implement;

import org.example.POJO.Phone;
import org.example.DAO.IPhoneDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;

import java.util.List;

public  class PhoneDAO  extends CRUD<Phone> implements IPhoneDAO{
    public PhoneDAO(Class<Phone> clazz) {
        super(clazz);
    }
    public Phone getHighestPrice() {
        Session session = this.getFactory().openSession();
        Criteria cr = session.createCriteria(Phone.class);
        cr.addOrder(Order.desc("Price"));
        Phone res = (Phone) cr.list().get(0);
        return  res;
    }

    public List<Phone> Sort() {
        Session session = this.getFactory().openSession();
        Criteria cr = session.createCriteria(Phone.class);
        cr.addOrder(Order.asc("Country")).addOrder(Order.asc("Price"));
        List result = cr.list();
        return result;
    }

    public boolean Above50Millions(Phone p) {
        return p.getPrice() > 50000000 ? true : false;
    }

    public Phone FirstPhone() {
        Session session = this.getFactory().openSession();
        Criteria cr = session.createCriteria(Phone.class);
        cr.add(Restrictions.and(Restrictions.gt("Price",15000000),Restrictions.eq("Color","blue")));
        List result = cr.list();
        return (Phone) result.get(result.size()-1);
    }

    @Override
    public List<Phone> getAll() {
        Session session = this.getFactory().openSession();
        Criteria cr = session.createCriteria(Phone.class);
        List results = cr.list();
        return results;
    }
}

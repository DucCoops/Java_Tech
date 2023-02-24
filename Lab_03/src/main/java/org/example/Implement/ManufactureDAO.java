package org.example.Implement;

import org.example.DAO.IManufactureDAO;
import org.example.POJO.Manufacture;
import org.example.POJO.Phone;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ManufactureDAO extends  CRUD<Manufacture> implements IManufactureDAO {


    public ManufactureDAO(Class<Manufacture> clazz) {
        super(clazz);
    }

    public boolean over100emp() {
        Session session = super.getFactory().openSession();
        Criteria cr = session.createCriteria(Manufacture.class);
        cr.add(Restrictions.lt("Employee",50));
        return cr.list().isEmpty();
    }

    public int sumEmp() {
        Session session = super.getFactory().openSession();
        Criteria cr = session.createCriteria(Manufacture.class);
        cr.setProjection(Projections.sum("Employee"));
        int res =  Integer.parseInt(String.valueOf(cr.list().get(0)));
        return  res;
    }

    public Manufacture lastInUSA() {
        Session session = super.getFactory().openSession();
        Criteria cr = session.createCriteria(Manufacture.class);
        try {
            cr.add(Restrictions.ge("Location","Vietnam"));
            List result =  cr.list();
            return  (Manufacture) result.get(0);
        }catch (Exception  e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Manufacture> getAll() {
        Session session = this.getFactory().openSession();
        Criteria cr = session.createCriteria(Manufacture.class);
        List results = cr.list();
        return results;
    }
}

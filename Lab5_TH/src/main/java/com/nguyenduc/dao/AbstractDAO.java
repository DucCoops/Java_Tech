package com.nguyenduc.dao;

import com.nguyenduc.model.Product;
import com.nguyenduc.model.Usertable;
import com.nguyenduc.repository.genericDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.Query;
import java.util.List;

public abstract class AbstractDAO<T> implements genericDAO<T> {
    private Class<T> clazz;
    protected AbstractDAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    public abstract List<T> getAll();
    private static final SessionFactory FACTORY;

    static {
        Configuration conf=new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Usertable.class);
        conf.addAnnotatedClass(Product.class);
        ServiceRegistry registry= new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        FACTORY =conf.buildSessionFactory(registry);
    }
    public static SessionFactory getFactory() {
        return FACTORY;
    }

    public boolean add(T t) {
        Session session = AbstractDAO.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(t);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public List<T> query(String hql,Object... parameters) {
        Session session=AbstractDAO.getFactory().openSession();
        Query q = session.createQuery(hql);
        for (int i = 1; i <= parameters.length ; i++) {
            q.setParameter(i, parameters[i-1]);
        }
        List<T> resultList =q.getResultList();
        return resultList;
    }


    public boolean remove(String id) {
        Session session=AbstractDAO.getFactory().openSession();
        Transaction tx = null;
        try{
            T obj = session.get(clazz,id);
            tx = session.getTransaction();
            tx.begin();
            session.delete(obj);
            tx.commit();
            return  true;
        }catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
//            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean update(T t) {
        Session session=AbstractDAO.getFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.getTransaction();
            tx.begin();
            session.update(t);
            tx.commit();
            return  true;
        }catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public T get(int id) {
        Session session=AbstractDAO.getFactory().openSession();
        try{
            T obj = session.get(clazz,id);
            return  obj;
        }catch (HibernateException e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public boolean remove(T t) {
        Session session=AbstractDAO.getFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.getTransaction();
            tx.begin();
            session.delete(t);
            tx.commit();
            return  true;
        }catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }
}

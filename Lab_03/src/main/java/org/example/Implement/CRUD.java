package org.example.Implement;
import org.example.DAO.Generic;
import org.example.POJO.Manufacture;
import org.example.POJO.Phone;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import java.util.List;

public abstract class CRUD<T> implements Generic<T> {
    private static  final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();
        conf.configure("hibernate.config.xml");
        conf.addAnnotatedClass(Phone.class);
        conf.addAnnotatedClass(Manufacture.class);
        //ServiceRegistry lớp trừ tượng
        ServiceRegistry registry= new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);
    }
    public static SessionFactory getFactory() {
        return FACTORY;
    }
    private Class<T> clazz;
    protected CRUD(Class<T> clazz) {
        this.clazz = clazz;
    }
    public abstract List<T> getAll();

    public boolean add(T p) {
        Session session = this.getFactory().openSession();
        try {
            session.getTransaction().begin();
            session.save(p);
            session.getTransaction().commit();
            return  true;
        }catch (Exception x){
            session.close();
        }
        return false;
    }

    public T get(String id) {
        Session session = this.getFactory().openSession();
        try {
            session.getTransaction().begin();
            T object =  session.get(clazz, id);
            return object;
        }catch (Exception x){
            x.printStackTrace();
            session.close();
        }
        return null;
    }
    public boolean remove(T p) {
        Session session = this.getFactory().openSession();
        try {
            session.getTransaction().begin();
            session.delete(this.get(p.toString()));
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            session.close();
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(T p) {
        Session session = this.getFactory().openSession();
        try {
            session.getTransaction().begin();
            session.update(p);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            session.close();
            e.printStackTrace();
        }
        return false;
    }
}

/**
 * @author ArnoldG6
 */
package org.una.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.una.entities.Year;
import org.una.util.HibernateUtil;

import java.util.List;

public class YearDAO {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<Year> listAll(){
        List<Year> years = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            years = session.createQuery("SELECT y from Year y").getResultList();
            transaction.commit();

        }catch (Exception e){
            years = null;
            if (transaction != null){
                transaction.rollback();
            }
            throw e;
        }finally{
            if (session != null)
                session.close();
        }
        return years;
    }





}

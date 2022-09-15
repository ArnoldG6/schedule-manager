/**
 * @author ArnoldG6
 */
package org.una.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.una.entities.Year;

import java.util.ArrayList;

public final class YearDAO extends DAO<Year> {

    private static YearDAO instance; //Singleton Pattern Object
    private YearDAO(){
        /*
         * Constructor shall be private so no one outside the class scope or a friend class scope can access it.
         */
    }
    static public YearDAO getInstance() {
        /*
         * @return the Singleton Pattern Object of YearDAO class.
         */
        if (instance == null)
            instance = new YearDAO();
        return instance;
    }

    @Override
    public ArrayList<Year> listAll(){
        ArrayList<Year> years;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            years = new ArrayList<Year>(session.createQuery("SELECT y from Year y").getResultList());
            transaction.commit();
            return years;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw e;
        }
    }
    @Override
    public void add(Year year){
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(year);
            transaction.commit();
        }catch(Exception e){
            if (transaction != null)
                transaction.rollback();
        }finally{
            if (session != null)
                session.close();
        }

    }

    @Override
    public void delete(Year year) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.remove(session.merge(year));
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            System.err.println(ex.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
    }


    @Override
    public void update(Year year) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.merge(year);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            System.err.println(ex.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
    }


    public ArrayList<Year>  searchEntitiesByField(String field, Object param){
        ArrayList<Year> years;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            StringBuilder querySB = new StringBuilder("SELECT y from Year y where y.");
            querySB.append(field);
            querySB.append("=:param");
            Query query= session.createQuery(querySB.toString());
            query.setParameter("param", param);
            years = new ArrayList<Year>(query.getResultList());
            transaction.commit();
            return years;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw e;
        }
    }
    public Year searchEntityByField(String field, Object param){
        Year year;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            StringBuilder querySB = new StringBuilder("SELECT y from Year y where y.");
            querySB.append(field);
            querySB.append("=:param");
            Query query= session.createQuery(querySB.toString());
            query.setParameter("param", param);
            year = (Year) query.getSingleResult();
            transaction.commit();
            return year;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw e;
        }
    }

}

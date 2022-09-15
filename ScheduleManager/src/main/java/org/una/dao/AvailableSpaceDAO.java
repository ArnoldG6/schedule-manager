/**
 * @author ArnoldG6
 */
package org.una.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.una.entities.AvailableSpace;
import org.una.entities.AvailableSpace;

import java.util.ArrayList;

public final class AvailableSpaceDAO extends DAO<AvailableSpace> {
    private static AvailableSpaceDAO instance; //Singleton Pattern Object
    private AvailableSpaceDAO(){
        /*
         * Constructor shall be private so no one outside the class scope or a friend class scope can access it.
         */
    }
    static public AvailableSpaceDAO getInstance() {
        /*
         * @return the Singleton Pattern Object of BlockDAO class.
         */
        if (instance == null)
            instance = new AvailableSpaceDAO();
        return instance;
    }
    @Override
    public ArrayList<AvailableSpace> listAll(){
        ArrayList<AvailableSpace> availableSpaces;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            availableSpaces = new ArrayList<AvailableSpace>(session.createQuery("SELECT a from AvailableSpace a").getResultList());
            transaction.commit();
            return availableSpaces;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw e;
        }
    }
    @Override
    public void add(AvailableSpace availableSpace){
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(availableSpace);
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
    public void delete(AvailableSpace availableSpace) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.remove(session.merge(availableSpace));
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
    public void update(AvailableSpace availableSpace) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.merge(availableSpace);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            System.err.println(ex.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
    }



    public ArrayList<AvailableSpace>  searchEntitiesByField(String field, Object param){
        ArrayList<AvailableSpace> availableSpaces;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            StringBuilder querySB = new StringBuilder("SELECT s from AvailableSpace s where s.");
            querySB.append(field);
            querySB.append("=:param");
            Query query= session.createQuery(querySB.toString());
            query.setParameter("param", param);
            availableSpaces = new ArrayList<AvailableSpace>(query.getResultList());
            transaction.commit();
            return availableSpaces;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw e;
        }
    }
    public AvailableSpace searchEntityByField(String field, Object param){
        AvailableSpace availableSpace;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            StringBuilder querySB = new StringBuilder("SELECT s from AvailableSpace s where s.");
            querySB.append(field);
            querySB.append("=:param");
            Query query= session.createQuery(querySB.toString());
            query.setParameter("param", param);
            availableSpace = (AvailableSpace) query.getSingleResult();
            transaction.commit();
            return availableSpace;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw e;
        }
    }
    
    
    
}

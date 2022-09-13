/**
 * @author ArnoldG6
 */
package org.una.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.una.entities.Student;
import org.una.exceptions.SingletonInstanceException;

import java.util.ArrayList;

public final class StudentDAO extends DAO<Student> {
    private static StudentDAO instance; //Singleton Pattern Object
    private StudentDAO(){
        /*
        * Constructor shall be private so no one outside the class scope or a friend class scope can access it.
        */
    }
    static public StudentDAO getInstance() {
        /*
         * @return the Singleton Pattern Object of YearDAO class.
         */
        if (instance == null)
            instance = new StudentDAO();
        return instance;
    }
    @Override
    public ArrayList<Student> listAll(){
        ArrayList<Student> students;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            students = new ArrayList<Student>(session.createQuery("SELECT s from Student s").getResultList());
            transaction.commit();
            return students;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw e;
        }
    }
    @Override
    public void add(Student student){
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(student);
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
    public void delete(Student student) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.remove(session.merge(student));
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
    public void update(Student student) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.merge(student);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            System.err.println(ex.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
    }
}

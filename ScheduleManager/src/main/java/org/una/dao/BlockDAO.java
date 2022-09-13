/**
 * @author ArnoldG6
 */
package org.una.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.una.entities.Block;

import java.util.ArrayList;

public final class BlockDAO extends DAO<Block> {
    private static BlockDAO instance; //Singleton Pattern Object
    private BlockDAO(){
        /*
         * Constructor shall be private so no one outside the class scope or a friend class scope can access it.
         */
    }
    static public BlockDAO getInstance() {
        /*
         * @return the Singleton Pattern Object of BlockDAO class.
         */
        if (instance == null)
            instance = new BlockDAO();
        return instance;
    }
    @Override
    public ArrayList<Block> listAll(){
        ArrayList<Block> blocks;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            blocks = new ArrayList<Block>(session.createQuery("SELECT b from Block b").getResultList());
            transaction.commit();
            return blocks;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw e;
        }
    }
    @Override
    public void add(Block block){
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(block);
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
    public void delete(Block block) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.remove(session.merge(block));
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
    public void update(Block block) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.merge(block);
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

/**
 * @author ArnoldG6
 */
package org.una.dao;

import org.hibernate.SessionFactory;
import org.una.util.HibernateUtil;

import java.util.ArrayList;

abstract public class DAO<T> {
    /* Abstract DAO class that forces to overwrite the methods listed below.
     * Every child of this class must be a singleton.
     */
    protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    abstract public ArrayList<T> listAll();
    abstract public void add(T t);
    abstract public void delete(T t);
    abstract public void update(T t);

    // This one needs to be implemented for all columns.
    /*
    abstract public T searchSingleEntityByColumn(Long id);
    abstract public T searchEntitiesByColumn(Long id);
    */
}

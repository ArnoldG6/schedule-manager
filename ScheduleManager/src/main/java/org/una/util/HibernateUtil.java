/**
 * @author ArnoldG6
 */
package org.una.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    //This scope initializes sessionFactory
    static {
        try {
            StandardServiceRegistry standartRegistry = new StandardServiceRegistryBuilder()
                    .configure("Hibernate/hibernate-cfg.xml").build();
            Metadata metaData = new MetadataSources(standartRegistry).getMetadataBuilder().build();
            sessionFactory = metaData.getSessionFactoryBuilder().build();
        } catch (Throwable th) {
            throw new ExceptionInInitializerError(th);
        }
    }
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

}

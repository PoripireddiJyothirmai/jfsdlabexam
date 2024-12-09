package com.klef.jfsd.exam.Hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {
        // Configure Hibernate
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Department.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            // Update operation using HQL
            String hql = "UPDATE Department SET name = ?1, location = ?2 WHERE departmentId = ?3";
            int updatedEntities = session.createQuery(hql)
                    .setParameter(1, "Updated Department Name")
                    .setParameter(2, "Updated Location")
                    .setParameter(3, 1) // Update department with ID = 1
                    .executeUpdate();

            transaction.commit();
            System.out.println(updatedEntities + " record(s) updated.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}

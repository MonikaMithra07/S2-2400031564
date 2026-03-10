package com.klef.fsad.exam;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ClientDemo {
    public static void main(String[] args) {
        // 1. Load Configuration and Map Entity Class
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Delivery.class);

        // 2. Build SessionFactory and Open Session
        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();

        try {
            // --- PART I: INSERT RECORD (Using Persistent Object) ---
            Transaction tx1 = session.beginTransaction();
            
            Delivery d = new Delivery();
            d.setId(101);
            d.setName("Food Package");
            d.setDate("2026-03-10");
            d.setStatus("Delivered");

            session.save(d); // Saves the object to the database
            tx1.commit();
            System.out.println("Step 1: Record Inserted Successfully!");

            // --- PART II: SELECT & DISPLAY (To verify it exists) ---
            String selectHql = "from Delivery";
            Query<Delivery> selectQuery = session.createQuery(selectHql, Delivery.class);
            List<Delivery> list = selectQuery.list();
            for (Delivery del : list) {
                System.out.println("Step 2: Found Delivery ID: " + del.getId() + " Name: " + del.getName());
            }

            // --- PART III: DELETE RECORD (Using HQL with Parameters) ---
            Transaction tx2 = session.beginTransaction();
            
            // Using a named parameter ':id' (safest for all versions)
            String hql = "delete from Delivery where id = :id";
            Query q = session.createQuery(hql);
            q.setParameter("id", 101);

            int result = q.executeUpdate();
            tx2.commit();
            
            System.out.println("Step 3: Records Deleted: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Always close session and factory to prevent memory leaks
            session.close();
            sf.close();
        }
    }
}
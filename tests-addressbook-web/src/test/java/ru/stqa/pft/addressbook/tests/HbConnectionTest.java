package ru.stqa.pft.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class HbConnectionTest {

   private SessionFactory sessionFactory;

   @BeforeClass
   protected void setUp() throws Exception {
      // A SessionFactory is set up once for an application!
      final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
              .configure() // configures settings from hibernate.cfg.xml
              .build();
      try {
         sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
      }
      catch (Exception e) {
         e.printStackTrace();
         // The registry would be destroyed by the SessionFactory, but we had trouble building the
         // so destroy it manually.
         StandardServiceRegistryBuilder.destroy(registry);
      }
   }

   @Test(enabled = true)
   public void testHbConnection() {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
/*
      List<GroupData> resultGroups = session.createQuery("from GroupData").list();
      for (GroupData group : resultGroups) {
         System.out.println(group);
      }
 */

      List<ContactData> resultContacts = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
      session.getTransaction().commit();
      session.close();

      for (ContactData contact : resultContacts) {
         System.out.println(contact);
         System.out.println(contact.getGroups());
      }

   }
}

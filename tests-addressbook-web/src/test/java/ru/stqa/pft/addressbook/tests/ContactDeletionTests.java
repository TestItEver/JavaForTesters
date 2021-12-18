package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBase{

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().homePage();
      if (app.contact().list().size() == 0) {
         app.contact().create(new ContactData()
                 .withFirstname("Alex")
                 .withLastname("Schneider")
                 .withCompany("Microsoft")
                 .withMobile("0123456789")
                 .withBday("10")
                 .withBmonth("September")
                 .withByear("1990")
                 .withEmail("alex@test.com")
                 .withGroup("Test1"));
      }
   }

   @Test(enabled = true)
   public void testContactDeletion() {

      Set<ContactData> before = app.contact().all();
      ContactData deletedContact = before.iterator().next();    // get random contact from set before for deletion
      app.contact().delete(deletedContact);                     // delete contact from the page
      app.goTo().homePage();

      Set<ContactData> after = app.contact().all();
      Assert.assertEquals(after.size(), before.size() - 1); // compare size of two sets: before and after

      //Compare elements of two lists: before and after deletion
      before.remove(deletedContact);
      Assert.assertEquals(after, before);
   }

   // *********************************** OTHER WAY FOR THE SAME THING *************************************************

   @Test(enabled = false)
   public void testContactDeletionWithLists() {
      //contact in the row = 1 will be deleted

      List<ContactData> before = app.contact().list();
      int index = before.size() - 1; // last contact in the list
      app.contact().delete(index);
      app.goTo().homePage();

      List<ContactData> after = app.contact().list();
      System.out.println("after" + after + " size: " + after.size());
      Assert.assertEquals(after.size(), before.size() - 1); // compare size of two lists: before and after

      //Compare elements of two lists: before and after deletion
      before.remove(index);
      Comparator<? super ContactData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(after, before);
   }

}

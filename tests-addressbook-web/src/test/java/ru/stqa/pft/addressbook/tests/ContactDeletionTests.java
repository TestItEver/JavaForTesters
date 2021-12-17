package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;

public class ContactDeletionTests extends TestBase{

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().homePage();
      if (app.contact().list().size() == 0) {
         app.contact().create(new ContactData("Alex", "Schneider", "Microsoft", "0123456789", "10", "September", "1990", "alex@test.com", "Test1"));
      }
   }

   @Test(enabled = true)
   public void testContactDeletion() {
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

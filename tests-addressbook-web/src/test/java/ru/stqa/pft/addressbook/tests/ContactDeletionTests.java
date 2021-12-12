package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;

public class ContactDeletionTests extends TestBase{

   @Test
   public void testContactDeletion() {
      //contact in the row = 1 will be deleted
      app.getNavigationHelper().goToHomepage();
      if (!app.getContactHelper().isThereAContact()) {
         app.getContactHelper().createContact(new ContactData("Alex", "Schneider", "Microsoft", "0123456789", "10", "September", "1990", "alex@test.com", "Test1"));
      }
      List<ContactData> before = app.getContactHelper().getContactList();
      //System.out.println("before " + before + " size: " + before.size());
      app.getContactHelper().selectContact(before.size() - 1); // select last contact
      app.getContactHelper().deleteContact();
      app.getNavigationHelper().goToHomepage();

      List<ContactData> after = app.getContactHelper().getContactList();
      //System.out.println("after" + after + " size: " + after.size());
      Assert.assertEquals(after.size(), before.size() - 1); // compare size of two lists: before and after

      //Compare elements of two lists: before and after deletion
      before.remove(before.size() - 1);
      Comparator<? super ContactData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(after, before);
   }
}

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

   @Test
   public void testContactModification() {

      app.getNavigationHelper().goToHomepage();
      if (!app.getContactHelper().isThereAContact()) {
         app.getContactHelper().createContact(new ContactData("Alex", "Schneider", "Microsoft", "0123456789", "10", "September", "1990", "alex@test.com", "Test1"));
      }
      List<ContactData> before = app.getContactHelper().getContactList();

      app.getContactHelper().initContactModification(before.size()); //last contact will be modified
      ContactData data = new ContactData (before.get(before.size() - 1).getId(), "Maria", "Ivanova", "Apple", "0987654321", "25", "January", "1987", "maria@test.com", null);
      app.getContactHelper().fillContactData(data, false);
      app.getContactHelper().submitContactModification();
      app.getNavigationHelper().goToHomepage();

      List<ContactData> after = app.getContactHelper().getContactList();
      Assert.assertEquals(after.size(), before.size()); // compare size of two lists: before and after modification

      //Compare elements of two lists: before and after deletion
      before.remove(before.size() - 1);
      before.add(data);
      Comparator<? super ContactData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(after, before);
   }

}

package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{

   @Test
   public void testContactDeletion() {
      //contact in the row = 1 will be deleted
      app.getNavigationHelper().goToHomepage();
      if (!app.getContactHelper().isThereAContact()) {
         app.getContactHelper().createContact(new ContactData("Alex", "Schneider", "Microsoft", "0123456789", "10", "September", "1990", "alex@test.com", "Test1"));
      }
      app.getContactHelper().selectContact();
      app.getContactHelper().deleteContact();
   }
}

package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

   @Test
   public void testContactModification() {

      app.getNavigationHelper().goToHomepage();
      if (!app.getContactHelper().isThereAContact()) {
         app.getContactHelper().createContact(new ContactData("Alex", "Schneider", "Microsoft", "0123456789", "10", "September", "1990", "alex@test.com", "Test1"));
      }
      app.getContactHelper().initContactModification(1); //contact in the row = rowIndex will be modified
      app.getContactHelper().fillContactData(new ContactData("Maria", "Ivanova", "Apple", "0987654321", "25", "January", "1987", "maria@test.com", null), false);
      app.getContactHelper().submitContactModification();
      app.getNavigationHelper().goToHomepage();

   }
}

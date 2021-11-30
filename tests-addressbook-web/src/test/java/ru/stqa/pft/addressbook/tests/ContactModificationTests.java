package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

   @Test
   public void testContactModification() {

      app.getContactHelper().selectContact();
      app.getContactHelper().initContactModification();
      app.getContactHelper().fillContactData(new ContactData("Maria", "Ivanova", "Apple", "0987654321", "25", "January", "1987", "maria@test.com", "Test2"));
      app.getContactHelper().submitContactModification();
      app.getNavigationHelper().returnToHomepage();

   }
}

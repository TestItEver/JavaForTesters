package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactData(new ContactData("Alex", "Schneider", "Microsoft", "0123456789", "10", "September", "1990", "alex@test.com", "Test1"), true);
    app.getContactHelper().submitContactForm();
    app.getNavigationHelper().returnToHomepage();
    app.getSessionHelper().logout();
  }

}

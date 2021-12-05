package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().createContact(new ContactData("Alex", "Schneider", "Microsoft", "0123456789", "10", "September", "1990", "alex@test.com", "Friends"));
    app.getSessionHelper().logout();
  }

}

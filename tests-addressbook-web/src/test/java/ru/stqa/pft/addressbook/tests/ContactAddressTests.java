package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().homePage();
      if (app.contact().list().size() == 0) {
         app.contact().create(new ContactData()
                 .withFirstname("Alex")
                 .withLastname("Schneider")
                 .withCompany("Microsoft")
                 .withMobilePhone("0123456789")
                 .withBday("10")
                 .withBmonth("September")
                 .withByear("1990")
                 .withEmail("alex@test.com"));
                 //.withGroup("Test1"));
      }
   }

   @Test
   public void testContactAddress () {

      ContactData contact = app.contact().all().iterator().next();
      ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

      assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
   }
}

package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase{

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
      }
   }

   @Test
   public void testContactEmails () {

      ContactData contact = app.contact().all().iterator().next();
      ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

      assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
   }

   private String mergeEmails(ContactData contact) {
      return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
              .stream().filter((s) -> ! s.equals("") ).collect(Collectors.joining("\n"));
   }
   /* 1. Convert ArrayList into stream
      2. Filter all Strings = "" out
      3. Collect all remaining Strings and join into one String (put as delimiter \n)
    */
}

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase{

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().homePage();
      if (app.db().contacts().size() == 0) {     // if (app.contact().list().size() == 0) -- List from interface (old)
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

   @Test(enabled = true)
   public void testContactDeletion() {

      Contacts before = app.db().contacts();        // Contacts before = app.contact().all();  -- List from interface

      ContactData deletedContact = before.iterator().next();    // get random contact from set before for deletion
      app.contact().delete(deletedContact);                     // delete contact from the page
      app.goTo().homePage();
      assertThat(app.contact().count(), equalTo(before.size() - 1));

      Contacts after = app.db().contacts();        // Contacts after = app.contact().all();   -- List from interface
      assertThat(after, equalTo(before.without(deletedContact)));
      verifyContactListInUI();
   }

   // *********************************** OTHER WAY FOR THE SAME THING *************************************************

   @Test(enabled = false)
   public void testContactDeletionWithLists() {
      //contact in the row = 1 will be deleted

      List<ContactData> before = app.contact().list();
      int index = before.size() - 1; // last contact in the list
      app.contact().delete(index);
      app.goTo().homePage();
      Assert.assertEquals(app.contact().count(), before.size() - 1); // compare size of two lists: before and after (testng)

      List<ContactData> after = app.contact().list();

      before.remove(index);
      Comparator<? super ContactData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(after, before);    // compare elements of two lists: before and after deletion (testng)
   }

}

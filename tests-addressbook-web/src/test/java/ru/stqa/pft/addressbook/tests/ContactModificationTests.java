package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().homePage();
      if (app.contact().list().size() == 0) {
         app.contact().create(new ContactData()
                 .withFirstname("Alex")
                 .withLastname("Schneider")
                 .withCompany("Microsoft")
                 .withMobile("0123456789")
                 .withBday("10")
                 .withBmonth("September")
                 .withByear("1990")
                 .withEmail("alex@test.com")
                 .withGroup("Test1"));
      }
   }

   @Test(enabled = true)
   public void testContactModification() {

      Contacts before = app.contact().all();

      ContactData modifiedContact = before.iterator().next();
      ContactData data = new ContactData()
              .withId(modifiedContact.getId())
              .withFirstname("Maria")
              .withLastname("Ivanova")
              .withCompany("Apple")
              .withMobile("0987654321")
              .withBday("25")
              .withBmonth("January")
              .withByear("1987")
              .withEmail("maria@test.com");
      app.contact().modify(data);
      app.goTo().homePage();

      Contacts after = app.contact().all();

      assertThat(after.size(), equalTo(before.size())); // compare size of two lists: before and after modification
      assertThat(after, equalTo(before.without(modifiedContact).withAdded(data)));      // compare two sets: before and after modification
      assertThat(after, equalTo(before.withModified(modifiedContact.getId(), data)));   // with extra method
   }

   // *********************************** OTHER WAY FOR THE SAME THING *************************************************

   @Test(enabled = false)
   public void testContactModificationWithLists() {

      List<ContactData> before = app.contact().list();
      int index = before.size() - 1;
      ContactData data = new ContactData()
              .withId(before.get(index).getId())
              .withFirstname("Maria")
              .withLastname("Ivanova")
              .withCompany("Apple")
              .withMobile("0987654321")
              .withBday("25")
              .withBmonth("January")
              .withByear("1987")
              .withEmail("maria@test.com");

      app.contact().modify(index, data);
      app.goTo().homePage();

      List<ContactData> after = app.contact().list();
      Assert.assertEquals(after.size(), before.size()); // compare size of two lists: before and after modification

      // Compare elements of two lists: before and after modification
      before.remove(index);
      before.add(data);
      Comparator<? super ContactData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(after, before);
   }

}

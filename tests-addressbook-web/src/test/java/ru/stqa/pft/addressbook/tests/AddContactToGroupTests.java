package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase{

   @BeforeMethod
   public void ensurePreconditions() {

      app.goTo().homePage();
      if (app.db().contacts().size() == 0) {
         File photo = new File ("src/test/resources/eule.png");
         app.contact().create(new ContactData()
                 .withFirstname("Alex")
                 .withLastname("Schneider")
                 .withCompany("Microsoft")
                 .withMobilePhone("0123456789")
                 .withBday("10")
                 .withBmonth("September")
                 .withByear("1990")
                 .withEmail("alex@test.com")
                 .withPhoto(photo));
      }
   }

   // select a contact randomly and add it to a group
   // if the selected contact is included already in all groups, then create a group and add contact to this group
   @Test
   public void testAddContactToGroup () {

      app.contact().selectViewForContactsInGroup("0");   // select view all groups

      Contacts before = app.db().contacts();
      Groups allGroups = app.db().groups();
      ContactData selectedContact = before.iterator().next();
      if (selectedContact.getGroups().size() == allGroups.size()) {
         app.goTo().groupPage();
         app.group().create(new GroupData().withName("New Group"));
         app.goTo().homePage();
         allGroups = app.db().groups();
      }
      GroupData myGroup = null;
      for (GroupData group : allGroups) {
         if (!selectedContact.getGroups().contains(group)) {
            myGroup = group;
            break;
         }
      }

      app.contact().addContactToGroup(selectedContact, myGroup);
      before.remove(selectedContact);
      before.add(selectedContact.inGroup(myGroup));
      Contacts after = app.db().contacts();
      assertThat(after, equalTo(before));

   }
}

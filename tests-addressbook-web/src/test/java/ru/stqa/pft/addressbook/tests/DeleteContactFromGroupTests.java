package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroupTests extends TestBase{

   @BeforeMethod
   public void ensurePreconditions() {

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
      if (app.db().groups().size() == 0) {
         app.goTo().groupPage();
         app.group().create(new GroupData().withName("New Group"));
      }

      app.goTo().homePage();
   }

   @Test
   public void testDeleteContactFromGroup() {

      Contacts allContacts = app.db().contacts();
      Groups allGroups = app.db().groups();
      ContactData selectedContact = null;
      GroupData myGroup = null;

      for (ContactData contact : allContacts) {           // find a contact with at least one group
         if (contact.getGroups().size() != 0) {
            selectedContact = contact;
            myGroup = contact.getGroups().iterator().next();
            break;
         }
      }

      if (selectedContact == null) {                     // if there is no contact with a group, add a contact to a group
         selectedContact = allContacts.iterator().next();
         myGroup = allGroups.iterator().next();
         app.contact().addContactToGroup(selectedContact, myGroup);
         selectedContact.inGroup(myGroup);
      }

      Contacts before = app.db().contacts();
      app.contact().selectViewForContactsInGroup(Integer.toString(myGroup.getId()));
      app.contact().deleteContactFromGroup(selectedContact, myGroup.getName());

      before.remove(selectedContact);
      before.add(selectedContact.removeGroup(myGroup));

      Contacts after = app.db().contacts();
      assertThat(after, equalTo(before));

   }
}

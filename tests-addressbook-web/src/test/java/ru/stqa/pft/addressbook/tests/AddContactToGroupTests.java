package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase{
   // select a contact randomly and add it to a group
   // if the selected contact is included already in all groups, then create a group and add contact to this group

   @Test
   public void testAddContactToGroup () {
      app.goTo().homePage();
      app.contact().selectAllGroups();

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
      before.add(selectedContact.inGroup(myGroup));

      Contacts after = app.db().contacts();
      assertThat(after, equalTo(before));

   }
}

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase{

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().groupPage();
      // if (app.group().list().size() == 0){   *** List groups from interface
      if (app.db().groups().size() == 0) {     // *** List groups from database
         app.group().create(new GroupData().withName("TestCreationX"));
      }
   }

   @Test(enabled = true)
   public void testGroupModification() {
      // Groups before = app.group().all();   -- List groups from interface
      Groups before = app.db().groups();    // List groups from database
      GroupData modifiedGroup = before.iterator().next();   // get random group from set before for modification
      GroupData data = new GroupData().withId(modifiedGroup.getId()).withName("TestModi").withHeader("Simply Test2").withFooter("xxx");

      app.group().modify(data);    // modify selected group on the page
      assertThat(app.group().count(), equalTo(before.size()));   // compare size of two sets: before and after modification (hamcrest)

      // Groups after = app.group().all();   -- List groups from interface
      Groups after = app.db().groups();   // List groups from database
      assertThat(after, equalTo(before.without(modifiedGroup).withAdded(data)));      // compare two sets: before and after modification
      assertThat(after, equalTo(before.withModified(modifiedGroup.getId(), data)));   // with extra method
      verifyGroupListInUI();
   }

   // *********************************** OTHER WAY FOR THE SAME THING ***************************************************
   // attention: because of changes for equals-Method the tests for comparison will fail!

   @Test(enabled = false)
   public void testGroupModificationWithLists() {

      List<GroupData> before = app.group().list();
      int index = before.size() - 1;
      GroupData data = new GroupData().withId(before.get(index).getId()).withName("Test2").withHeader("Simply Test2").withFooter("xxx");
      app.group().modify(index, data);
      Assert.assertEquals(app.group().count(), before.size()); // compare size of two lists: before and after modification (testng)

      List<GroupData> after = app.group().list();
      before.remove(index);
      before.add(data);

      Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(after, before);  //Compare two lists after sort (testng)
   }

}

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
      if (app.group().list().size() == 0){
         app.group().create(new GroupData().withName("TestCreationX"));
      }
   }

   @Test(enabled = false)
   public void testGroupModification() {

      Groups before = app.group().all();
      GroupData modifiedGroup = before.iterator().next();   // get random group from set before for modification
      GroupData data = new GroupData().withId(modifiedGroup.getId()).withName("TestModi").withHeader("Simply Test2").withFooter("xxx");

      app.group().modify(data);    // modify selected group on the page
      Groups after = app.group().all();

      assertThat(after.size(), equalTo(before.size()));   // compare size of two sets: before and after modification (hamcrest)
      assertThat(after, equalTo(before.without(modifiedGroup).withAdded(data)));      // compare two sets: before and after modification
      assertThat(after, equalTo(before.withModified(modifiedGroup.getId(), data)));   // with extra method
   }

   // *********************************** OTHER WAY FOR THE SAME THING ***************************************************

   @Test(enabled = false)
   public void testGroupModificationWithLists() {

      List<GroupData> before = app.group().list();
      int index = before.size() - 1;
      GroupData data = new GroupData().withId(before.get(index).getId()).withName("Test2").withHeader("Simply Test2").withFooter("xxx");
      app.group().modify(index, data);

      List<GroupData> after = app.group().list();
      Assert.assertEquals(after.size(), before.size()); // compare size of two lists: before and after modification (testng)

      before.remove(index);
      before.add(data);

      Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(after, before);  //Compare two lists after sort (testng)
   }

}

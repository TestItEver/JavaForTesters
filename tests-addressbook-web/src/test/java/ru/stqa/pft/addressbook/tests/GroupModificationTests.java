package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase{

   @Test
   public void testGroupModification() {

      app.getNavigationHelper().gotoGroupPage();
      if (!app.getGroupHelper().isThereAGroup()){
         app.getGroupHelper().createGroup(new GroupData("Test1", null, null));
      }
      // int before = app.getGroupHelper().getGroupCount();
      List<GroupData> before = app.getGroupHelper().getGroupList();

      app.getGroupHelper().selectGroup(before.size() - 1);
      app.getGroupHelper().initGroupModification();
      GroupData data = new GroupData(before.get(before.size() - 1).getId(),  "Test2", "Simply Test2", "xxx");
      app.getGroupHelper().fillGroupForm(data);
      app.getGroupHelper().submitGroupModification();
      app.getGroupHelper().returnToGroupPage();

      // int after = app.getGroupHelper().getGroupCount();
      List<GroupData> after = app.getGroupHelper().getGroupList();
      Assert.assertEquals(after.size(), before.size()); // compare size of two lists: before and after modification

      //Compare elements of two lists: before and after modification: HashSet
      before.remove(before.size() - 1);
      before.add(data);
      Assert.assertEquals(new HashSet<Object>(after) , new HashSet<Object>(before));

      //Compare two lists after sort
      Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(after, before);
   }

}

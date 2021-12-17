package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase{

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().groupPage();
      if (app.group().list().size() == 0){
         app.group().create(new GroupData("Test1", null, null));
      }
   }

   @Test
   public void testGroupModification() {

      List<GroupData> before = app.group().list();
      int index = before.size() - 1;
      GroupData data = new GroupData(before.get(index).getId(),  "Test2", "Simply Test2", "xxx");

      app.group().modify(index, data);

      List<GroupData> after = app.group().list();
      Assert.assertEquals(after.size(), before.size()); // compare size of two lists: before and after modification

      //Compare elements of two lists: before and after modification: HashSet
      before.remove(index);
      before.add(data);
      Assert.assertEquals(new HashSet<Object>(after) , new HashSet<Object>(before));

      //Compare two lists after sort
      Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(after, before);
   }

}

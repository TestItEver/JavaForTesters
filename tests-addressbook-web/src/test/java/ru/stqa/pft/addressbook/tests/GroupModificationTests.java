package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase{

   @Test
   public void testGroupModification() {

      app.getNavigationHelper().gotoGroupPage();
      app.getGroupHelper().selectGroup();
      app.getGroupHelper().initGroupModification();
      app.getGroupHelper().fillGroupForm(new GroupData("Test2", "Simply Test2", "xxx"));
      app.getGroupHelper().submitGroupModification();
      app.getGroupHelper().returnToGroupPage();

   }
}

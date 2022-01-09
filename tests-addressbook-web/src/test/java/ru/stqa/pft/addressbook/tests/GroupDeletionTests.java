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


public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.db().groups().size() == 0){           // if (app.group().list().size() == 0) --List from interface
      app.group().create(new GroupData().withName("TestCreationX"));
    }
  }

  @Test(enabled = true)
  public void testGroupDeletion() throws Exception {

    Groups before = app.db().groups();           // Groups before = app.group().all(); --List from interface
    GroupData deletedGroup = before.iterator().next();   // get random group from set before for deletion
    app.group().delete(deletedGroup);    // delete group from the page
    assertThat(app.group().count(), equalTo(before.size() - 1));   // compare size of two sets: before and after deletion (hamcrest)

    Groups after = app.db().groups();           // Groups after = app.group().all();   --List from interface
    assertThat(after, equalTo(before.without(deletedGroup)));     // compare elements of two sets: before and after deletion
    verifyGroupListInUI();
  }

  // *********************************** OTHER WAY FOR THE SAME THING ***************************************************

  @Test(enabled = false)
  public void testGroupDeletionWithLists() throws Exception {

    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    app.group().delete(index);
    Assert.assertEquals(app.group().count(), before.size() - 1);   // compare size of two lists: before and after deletion (testng)
    List<GroupData> after = app.group().list();

    before.remove(index);    // remove deleted group from the list before
    Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);   //Compare elements of two lists: before and after deletion (testng)
  }

}

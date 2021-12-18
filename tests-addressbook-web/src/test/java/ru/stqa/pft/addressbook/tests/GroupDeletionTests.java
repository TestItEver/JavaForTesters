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
    if (app.group().list().size() == 0){
      app.group().create(new GroupData().withName("TestCreationX"));
    }
  }

  @Test(enabled = true)
  public void testGroupDeletion() throws Exception {

    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();   // get random group from set before for deletion
    app.group().delete(deletedGroup);    // delete group from the page
    Groups after = app.group().all();

    assertThat(after.size(), equalTo(before.size() - 1));   // compare size of two sets: before and after deletion (hamcrest)
    assertThat(after, equalTo(before.without(deletedGroup))); //Compare elements of two sets: before and after deletion
  }

  // *********************************** OTHER WAY FOR THE SAME THING ***************************************************

  @Test(enabled = false)
  public void testGroupDeletionWithLists() throws Exception {

    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    app.group().delete(index);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() - 1);    //compare size of two lists: before and after deletion (testng)

    before.remove(index);    // remove deleted group from the list before
    Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);   //Compare elements of two lists: before and after deletion (testng)
  }

}

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage(); // precondition

    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData().withName("TestCreation");
    app.group().create(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() + 1); // compare size of two sets: before and after creation

    // stream of GroupData(s) will be converted into stream of Integers due to the anonymous function (o) -> o.getId()
    // then maximum of the Integers in stream will be calculated and returned as Integer
    int max = after.stream().mapToInt((objectGroupData) -> objectGroupData.getId()).max().getAsInt();
    group.withId(max);
    before.add(group);
    Assert.assertEquals(after, before);     // compare elements of two sets: before and after creation
  }

  // *********************************** OTHER WAY FOR THE SAME THING *************************************************

  @Test(enabled = false)
  public void testGroupCreationWithLists() throws Exception {
    app.goTo().groupPage(); // precondition

    List<GroupData> before = app.group().list();
    GroupData group = new GroupData().withName("Test4");
    app.group().create(group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() + 1);     // compare size of two lists: before and after creation

    // find out the id of created group --> maximum of all id's
    int max = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
    group.withId(max);
    before.add(group);

    Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);       // compare elements of two lists: before and after creation
  }

}

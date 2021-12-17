package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage(); // precondition

    List<GroupData> before = app.group().list();
    GroupData group = new GroupData("Test4", null, null);
    app.group().create(group);

    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() + 1); // compare size of two lists: before and after creation

    // compare elements of two lists: before and after creation
    int max = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
    group.setId(max);
    before.add(group);
    // first way (important: equals() and hash() should include id!):
    // Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));

    // other way: comparison of sorted lists
    Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }

}

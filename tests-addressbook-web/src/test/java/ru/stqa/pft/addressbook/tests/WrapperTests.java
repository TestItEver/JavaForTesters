package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.MySetWrapper;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class WrapperTests extends TestBase {

  @Test(enabled = true)
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage(); // precondition

    MySetWrapper before = app.group().mySet();
    System.out.println(before);
    GroupData group = new GroupData().withName("TestCreation");
    app.group().create(group);
    MySetWrapper after = app.group().mySet();
    System.out.println(after);

    assertThat(after.size(), equalTo(before.size() + 1));  // compare size of two sets: before and after creation (hamcrest)
    assertThat(after, equalTo(                                      // compare elements of two sets: before and after creation (hamcrest)
            before.withAdded(before, group.withId(after.stream().mapToInt((objectGroupData) -> objectGroupData.getId()).max().getAsInt()))));
  }

}

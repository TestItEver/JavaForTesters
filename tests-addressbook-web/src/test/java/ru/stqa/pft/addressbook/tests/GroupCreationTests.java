package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.json.TypeToken;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroupsCSV() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    // list.add(new Object[]{new GroupData().withName("test1").withHeader("header1").withFooter("footer1")});

    try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/groups.csv"))) {
      String line = reader.readLine();
      while (line != null) {
        String[] split = line.split(";");
        list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
        line = reader.readLine();
      }
      return list.iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validGroupsXML() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/groups.xml"))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.allowTypes(new Class[]{GroupData.class});
      xstream.processAnnotations(GroupData.class);
      // xstream.addPermission(AnyTypePermission.ANY);
      List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
      return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validGroupsJSON() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/groups.json"))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {}.getType()); // List<GroupData>.class
      return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validGroupsJSON")
  public void testGroupCreation(GroupData group) throws Exception {

    app.goTo().groupPage(); // precondition

    Groups before = app.db().groups();      // Groups before = app.group().all();  --List from interface
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() + 1));  // compare size of two sets: before and after creation (hamcrest)

    Groups after = app.db().groups();       // Groups after = app.group().all();   --List from interface
    assertThat(after, equalTo(                                           // compare elements of two sets: before and after creation (hamcrest)
            before.withAdded(group.withId(after.stream().mapToInt((objectGroupData) -> objectGroupData.getId()).max().getAsInt()))));
    /*
    ++ stream of GroupData(s) will be converted into stream of Integers due to the anonymous function (o) -> o.getId() ++
    ++ then maximum of the Integers in stream will be calculated and returned as Integer ++
    int max = after.stream().mapToInt((objectGroupData) -> objectGroupData.getId()).max().getAsInt();
     */
    verifyGroupListInUI();
  }

  // *********************************** OTHER WAY FOR THE SAME THING *************************************************
  // attention: because of changes for equals-Method the tests for comparison will fail!

  @Test(enabled = false)
  public void testGroupCreationWithLists() throws Exception {
    app.goTo().groupPage(); // precondition

    List<GroupData> before = app.group().list();
    GroupData group = new GroupData().withName("Test4");
    app.group().create(group);
    Assert.assertEquals(app.group().count(), before.size() + 1);     // compare size of two lists: before and after creation (testng)

    List<GroupData> after = app.group().list();
    // find out the id of created group --> maximum of all id's
    int max = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
    group.withId(max);
    before.add(group);

    Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);       // compare elements of two lists: before and after creation (testng)
  }

}

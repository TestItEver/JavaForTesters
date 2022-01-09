package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.json.TypeToken;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class ContactCreationTests extends TestBase{

  //**********************************************DATA PROVIDER*********************************************************

  @DataProvider
  public Iterator<Object[]> validContactsXML() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.xml"));
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
      xml += line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.allowTypes(new Class[]{ContactData.class});
    xstream.processAnnotations(ContactData.class);
    // xstream.addPermission(AnyTypePermission.ANY);
    List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
    return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @DataProvider
  public Iterator<Object[]> validContactsJSON() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.json"));
    String json = "";
    String line = reader.readLine();
    while (line != null) {
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {}.getType()); // List<ContactData>.class
    return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

//************************************************END DATA PROVIDER******************************************************


  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.db().groups().size() == 0){
      app.group().create(new GroupData().withName("TestCreationX"));
    }
  }

  @Test(dataProvider = "validContactsJSON")
  public void testContactCreationDataProvider(ContactData newContact) throws Exception {

    Contacts before = app.db().contacts();        // Contacts before = app.contact().all(); -- List from interface (old)

    Groups groups = app.db().groups();
    File photo = new File ("src/test/resources/eule.png");
    newContact.withPhoto(photo).inGroup(groups.iterator().next());

    app.contact().create(newContact);    // add new contact on the page
    assertThat(app.contact().count(), equalTo(before.size() + 1));

    Contacts after = app.db().contacts();    // Contacts after = app.contact().all();   -- List from interface (old)
    assertThat(after, equalTo
            (before.withAdded(newContact.withId
                    (after.stream().mapToInt((objectContactData) -> objectContactData.getId()).max().getAsInt()))));
    verifyContactListInUI();
  }

  // Without DataProvider
  @Test(enabled = false)
  public void testContactCreation() throws Exception {
    app.goTo().homePage(); // precondition

    Contacts before = app.db().contacts();     // Contacts before = app.contact().all();
    File photo = new File ("src/test/resources/eule.png");
    ContactData newContact = new ContactData()
            .withFirstname("Alex")
            .withLastname("Schneider")
            .withPhoto(photo)
            .withCompany("Microsoft")
            .withMobilePhone("0123456789")
            .withBday("10")
            .withBmonth("September")
            .withByear("1990")
            .withEmail("alex@test.com");
            //.withGroup("Test1");
    app.contact().create(newContact);    // add new contact on the page
    assertThat(app.contact().count(), equalTo(before.size() + 1));

    Contacts after = app.db().contacts();    // Contacts after = app.contact().all();
    assertThat(after, equalTo
            (before.withAdded(newContact.withId
                    (after.stream().mapToInt((objectContactData) -> objectContactData.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File ("src/test/resources/eule.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }

}

  /*
  // *********************************** OTHER WAY FOR THE SAME THING *************************************************
  // attention: because of changes for equals-Method the tests for comparison will fail!

  @Test(enabled = false)
  public void testContactCreationWithLists() throws Exception {
    app.goTo().homePage(); // precondition

    List<ContactData> before = app.contact().list();
    ContactData data = new ContactData()
            .withFirstname("Alex")
            .withLastname("Schneider")
            .withCompany("Microsoft")
            .withMobilePhone("0123456789")
            .withBday("10")
            .withBmonth("September")
            .withByear("1990")
            .withEmail("alex@test.com");
            //.withGroup("Test1");
    app.contact().create(data);
    Assert.assertEquals(app.contact().count(), before.size() + 1); // compare size of two lists: before and after creation

    List<ContactData> after = app.contact().list();

    // Compare elements of two lists: before and after creation
    Comparator<? super ContactData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    int max = after.stream().max(byId).get().getId(); // searching after max of id in the "after list"
    data.withId(max); // set correct id of the created contact
    before.add(data); // add new data to the "before list"
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }
   */



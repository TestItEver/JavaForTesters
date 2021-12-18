package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test(enabled = true)
  public void testContactCreation() throws Exception {
    app.goTo().homePage(); // precondition

    List<ContactData> before = app.contact().list();
    ContactData data = new ContactData()
            .withFirstname("Alex")
            .withLastname("Schneider")
            .withCompany("Microsoft")
            .withMobile("0123456789")
            .withBday("10")
            .withBmonth("September")
            .withByear("1990")
            .withEmail("alex@test.com")
            .withGroup("Test1");
    app.contact().create(data);

    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1); // compare size of two lists: before and after creation

    // Compare elements of two lists: before and after creation
    Comparator<? super ContactData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    int max = after.stream().max(byId).get().getId(); // searching after max of id in the "after list"
    data.withId(max); // set correct id of the created contact
    before.add(data); // add new data to the "before list"
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }

}

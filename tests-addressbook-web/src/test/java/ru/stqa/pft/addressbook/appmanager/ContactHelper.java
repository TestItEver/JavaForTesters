package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
   private Contacts contactCache = null;

   public ContactHelper(WebDriver wd) {
      super(wd);
   }

   public void returnToHomepage() {
      click(By.linkText("home page"));
   }

   public void initContactCreation() {
      click(By.linkText("add new"));
   }

   public void submitContactForm() {
      //click(By.xpath("//div[@id='content']/form/input[21]"));
      click(By.name("submit"));
   }

   public void fillContactData(ContactData contactData, boolean creation) {
      type(By.name("firstname"),contactData.getFirstname());
      type(By.name("lastname"), contactData.getLastname());
      type(By.name("company"), contactData.getCompany());
      // attach(By.name("photo"), contactData.getPhoto());
      type(By.name("mobile"), contactData.getMobilePhone());
      if (contactData.getBday() != null) {
         click(By.name("bday"));
         new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactData.getBday());
      }
      if (contactData.getBmonth() != null) {
         click(By.name("bmonth"));
         new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactData.getBmonth());
      }
      type(By.name("byear"), contactData.getByear());
      type(By.name("email"), contactData.getEmail());

      if (creation) {
         attach(By.name("photo"), contactData.getPhoto());
         try {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
         } catch (NoSuchElementException e) {
            //todo
            System.out.println("TEST-LOG: group " + contactData.getGroup() + " is not available!");
         }
      } else {
         Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
   }

   public void initContactModification(int rowIndex) {
      String xpath = "(//img[@alt='Edit'])[" + (rowIndex + 1) + "]";   // click(By.xpath("(//img[@alt='Edit'])[2]"));
      click(By.xpath(xpath));
   }

   private void initContactModificationById(int id) {
      // wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
      // wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
      wd.findElement(By.xpath(String.format("//a[@href='edit.php?id=%s']", id))).click();
   }

   public void selectContact(int index) {
      wd.findElements(By.name("selected[]")).get(index).click();
   }

   private void selectContactById(int id) {
      // wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
      wd.findElement(By.xpath("//input[@id = '" + id + "']")).click();
   }

   public void submitContactModification() {
      click(By.name("update"));
   }

   public void deleteContact() {
      click(By.xpath("//input[@value='Delete']"));
      wd.switchTo().alert().accept();
   }

   public int count() {
      return wd.findElements(By.name("selected[]")).size();
   }

   public void create(ContactData contact) {
      initContactCreation();
      fillContactData(contact, true);
      submitContactForm();
      contactCache = null;
      returnToHomepage();
   }

   public void modify(int index, ContactData data) {
      initContactModification(index); //last contact will be modified
      fillContactData(data, false);
      submitContactModification();
      contactCache = null;
   }

   public void modify(ContactData data) {
      initContactModificationById(data.getId());
      fillContactData(data, false);
      submitContactModification();
      contactCache = null;
   }

   public void delete(int index) {
      selectContact(index);
      deleteContact();
      contactCache = null;
   }

   public void delete(ContactData deletedContact) {
      selectContactById(deletedContact.getId());
      deleteContact();
      contactCache = null;
   }

   public boolean isThereAContact() {
      return isElementPresent(By.name("selected[]"));
   }

   public Contacts all() {
      if (contactCache != null) {
         return new Contacts(contactCache);
      }
      contactCache = new Contacts();
      List<WebElement> rows = wd.findElements(By.tagName("tr")); // subject line is included

      for (int i = 1; i < rows.size(); i++) {
         List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
         int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
         String lastname = cells.get(1).getText();
         String firstname = cells.get(2).getText();
         String address = cells.get(3).getText();
         String allEmails = cells.get(4).getText();
         String allPhones = cells.get(5).getText();

         ContactData contact = new ContactData()
                 .withId(id)
                 .withFirstname(firstname)
                 .withLastname(lastname)
                 .withAddress(address)
                 .withAllEmails(allEmails)
                 .withAllPhones(allPhones);
         contactCache.add(contact);
      }
      return new Contacts(contactCache);
   }

   public List<ContactData> list() {
      List<ContactData> contacts = new ArrayList<ContactData>();
      List<WebElement> rows = wd.findElements(By.tagName("tr")); // subject line is included
      // List<WebElement> rows = wd.findElements(By.xpath("//tr[@name = 'entry']")); --> subject line is not included

      for (int i = 1; i < rows.size(); i++) {
         List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
         // List<WebElement> cells = rows.get(i).findElements(By.xpath(".//td"));
         int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
         String lastname = cells.get(1).getText();
         String firstname = cells.get(2).getText();
         String email = cells.get(4).getText();
         String mobile = cells.get(5).getText();

         ContactData contact = new ContactData()
                 .withId(id)
                 .withFirstname(firstname)
                 .withLastname(lastname)
                 .withMobilePhone(mobile)
                 .withEmail(email);

         contacts.add(contact);
      }
      return contacts;
   }

   public ContactData infoFromEditForm(ContactData contact) {
      initContactModificationById(contact.getId());

      String firstname = wd.findElement(By.name(("firstname"))).getAttribute("value");
      String lastname = wd.findElement(By.name(("lastname"))).getAttribute("value");
      String address = wd.findElement(By.name(("address"))).getAttribute("value");
      String homePhone = wd.findElement(By.name(("home"))).getAttribute("value");
      String mobilePhone = wd.findElement(By.name(("mobile"))).getAttribute("value");
      String workPhone = wd.findElement(By.name(("work"))).getAttribute("value");
      String email = wd.findElement(By.name(("email"))).getAttribute("value");
      String email2 = wd.findElement(By.name(("email2"))).getAttribute("value");
      String email3 = wd.findElement(By.name(("email3"))).getAttribute("value");

      contact.withFirstname(firstname).withLastname(lastname).withAddress(address)
              .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone)
              .withEmail(email).withEmail2(email2).withEmail3(email3);

      return contact;
   }
}

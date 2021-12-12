package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

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
      click(By.xpath("//div[@id='content']/form/input[21]"));
   }

   public void fillContactData(ContactData contactData, boolean creation) {
      type(By.name("firstname"),contactData.getFirstname());
      type(By.name("lastname"), contactData.getLastname());
      type(By.name("company"), contactData.getCompany());
      type(By.name("mobile"), contactData.getMobile());
      click(By.name("bday"));
      new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactData.getBday());
      click(By.name("bmonth"));
      new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactData.getBmonth());
      type(By.name("byear"), contactData.getByear());
      type(By.name("email"), contactData.getEmail());

      if (creation) {
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
      String xpath = "(//img[@alt='Edit'])[" + rowIndex + "]";
      //click(By.xpath("(//img[@alt='Edit'])[2]"));
      click(By.xpath(xpath));
   }

   public void selectContact(int index) {
      wd.findElements(By.name("selected[]")).get(index).click();
      //click(By.name("selected[]")); //first contact selected
   }

   public void submitContactModification() {
      click(By.name("update"));
   }

   public void deleteContact() {
      click(By.xpath("//input[@value='Delete']"));
      wd.switchTo().alert().accept();
   }

   public void createContact(ContactData contact) {
      initContactCreation();
      fillContactData(contact, true);
      submitContactForm();
      returnToHomepage();
   }

   public boolean isThereAContact() {
      return isElementPresent(By.name("selected[]"));
   }

   public List<ContactData> getContactList() {
      List<ContactData> contacts = new ArrayList<ContactData>();
      List<WebElement> rows = wd.findElements(By.tagName("tr"));

      for (int i =1; i < rows.size(); i++) {
         List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
         int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
         String lastname = cells.get(1).getText();
         String firstname = cells.get(2).getText();
         String email = cells.get(4).getText();
         String mobile = cells.get(5).getText();

         ContactData contact = new ContactData(id, firstname, lastname, null, mobile, null, null, null, email, null);
         contacts.add(contact);
      }
      return contacts;
   }
}

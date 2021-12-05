package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

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
         new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      } else {
         Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
   }

   public void initContactModification(int rowIndex) {
      String xpath = "(//img[@alt='Edit'])[" + rowIndex + "]";
      //click(By.xpath("(//img[@alt='Edit'])[2]"));
      click(By.xpath(xpath));
   }

   public void selectContact() {
      click(By.name("selected[]")); //first contact selected
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
}

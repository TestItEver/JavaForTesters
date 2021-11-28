package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
   public WebDriver wd;

   public void init() {
      wd = new ChromeDriver();
      // wd = new FirefoxDriver();
      wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      wd.get("http://localhost/addressbook/addressbook/");
      login("admin", "secret");
   }

   private void login(String username, String password) {
     wd.findElement(By.name("user")).click();
     wd.findElement(By.name("user")).clear();
     wd.findElement(By.name("user")).sendKeys(username);
     wd.findElement(By.name("pass")).click();
     wd.findElement(By.name("pass")).clear();
     wd.findElement(By.name("pass")).sendKeys(password);
     wd.findElement(By.xpath("//input[@value='Login']")).click();
   }

   public void logout() {
     wd.findElement(By.linkText("Logout")).click();
   }

   public void returnToGroupPage() {
     wd.findElement(By.linkText("group page")).click();
   }

   public void submitGroupForm() {
     wd.findElement(By.name("submit")).click();
   }

   public void fillGroupForm(GroupData groupData) {
     wd.findElement(By.name("group_name")).click();
     wd.findElement(By.name("group_name")).clear();
     wd.findElement(By.name("group_name")).sendKeys(groupData.getGroupname());
     wd.findElement(By.name("group_header")).click();
     wd.findElement(By.name("group_header")).clear();
     wd.findElement(By.name("group_header")).sendKeys(groupData.getGroupheader());
     wd.findElement(By.name("group_footer")).click();
     wd.findElement(By.name("group_footer")).clear();
     wd.findElement(By.name("group_footer")).sendKeys(groupData.getGroupfooter());
   }

   public void initGroupCreation() {
     wd.findElement(By.name("new")).click();
   }

   public void gotoGroupPage() {
     wd.findElement(By.linkText("groups")).click();
   }

   public void stop() {
      wd.quit();
   }

   private boolean isElementPresent(By by) {
     try {
       wd.findElement(by);
       return true;
     } catch (NoSuchElementException e) {
       return false;
     }
   }

   private boolean isAlertPresent() {
     try {
       wd.switchTo().alert();
       return true;
     } catch (NoAlertPresentException e) {
       return false;
     }
   }

   public void deleteSelectedGroups() {
     wd.findElement(By.xpath("//div[@id='content']/form/input[5]")).click();
   }

   public void selectGroup() {
     wd.findElement(By.name("selected[]")).click();
   }
}

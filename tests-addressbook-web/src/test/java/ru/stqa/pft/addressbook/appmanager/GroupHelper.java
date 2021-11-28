package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupHelper {
   private WebDriver wd;

   public GroupHelper(WebDriver wd) {
      this.wd = wd;
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

   public void deleteSelectedGroups() {
     wd.findElement(By.xpath("//div[@id='content']/form/input[5]")).click();
   }

   public void selectGroup() {
     wd.findElement(By.name("selected[]")).click();
   }
}

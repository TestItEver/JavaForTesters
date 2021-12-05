package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {

   public GroupHelper(WebDriver wd) {
      super(wd);
   }

   public void returnToGroupPage() {
     click(By.linkText("group page"));
   }

   public void submitGroupForm() {
      click(By.name("submit"));
   }

   public void fillGroupForm(GroupData groupData) {
      type(By.name("group_name"), groupData.getGroupname());
      type(By.name("group_header"), groupData.getGroupheader());
      type(By.name("group_footer"), groupData.getGroupfooter());
   }

   public void initGroupCreation() {
      click(By.name("new"));
   }

   public void deleteSelectedGroups() {
      click(By.xpath("//div[@id='content']/form/input[5]"));
   }

   public void selectGroup() {
      click(By.name("selected[]"));
   }

   public void initGroupModification() {
      click(By.name("edit"));
   }

   public void submitGroupModification() {
      click(By.name("update"));
   }

   public void createGroup(GroupData group) {
      initGroupCreation();
      fillGroupForm(group);
      submitGroupForm();
      returnToGroupPage();
   }

   public boolean isThereAGroup() {
      return isElementPresent(By.name("selected[]"));
   }
}

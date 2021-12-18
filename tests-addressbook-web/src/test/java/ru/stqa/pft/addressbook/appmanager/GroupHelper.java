package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.MySet;

import java.util.ArrayList;
import java.util.List;

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
      type(By.name("group_name"), groupData.getName());
      type(By.name("group_header"), groupData.getHeader());
      type(By.name("group_footer"), groupData.getFooter());
   }

   public void initGroupCreation() {
      click(By.name("new"));
   }

   public void deleteSelectedGroups() {
      click(By.xpath("//div[@id='content']/form/input[5]"));
   }

   public void selectGroup(int index) {
      wd.findElements(By.name("selected[]")).get(index).click();
   }

   private void selectGroupById(int id) {
      // wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
      wd.findElement(By.xpath("//input[@value = '" + id + "']")).click();
   }

   public void initGroupModification() {
      click(By.name("edit"));
   }

   public void submitGroupModification() {
      click(By.name("update"));
   }

   public void create(GroupData group) {
      initGroupCreation();
      fillGroupForm(group);
      submitGroupForm();
      returnToGroupPage();
   }

   public void modify(int index, GroupData data) {
      selectGroup(index);
      initGroupModification();
      fillGroupForm(data);
      submitGroupModification();
      returnToGroupPage();
   }

   public void modify(GroupData data) {
      selectGroupById(data.getId());
      initGroupModification();
      fillGroupForm(data);
      submitGroupModification();
      returnToGroupPage();
   }

   public void delete(int index) {
      selectGroup(index);
      deleteSelectedGroups();
      returnToGroupPage();
   }

   public void delete(GroupData group) {
      selectGroupById(group.getId());
      deleteSelectedGroups();
      returnToGroupPage();
   }

   public boolean isThereAGroup() {
      return isElementPresent(By.name("selected[]"));
   }

   public int getGroupCount() {
      return wd.findElements(By.name("selected[]")).size();
   }

   public List<GroupData> list() {
      List<GroupData> groupsList = new ArrayList<>();
      List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
      for (WebElement element : elements){
         String name = element.getText();
         int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
         groupsList.add(new GroupData().withId(id).withName(name));
      }
      return groupsList;
   }

   public Groups all() {
      Groups groups = new Groups();
      List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
      for (WebElement element : elements){
         String name = element.getText();
         int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
         groups.add(new GroupData().withId(id).withName(name));
      }
      return groups;
   }

   public MySet mySet() {
      MySet groups = new MySet();
      List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
      for (WebElement element : elements){
         String name = element.getText();
         int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
         groups.add(new GroupData().withId(id).withName(name));
      }
      return groups;
   }

   /* Predecessor for public Groups all()
   public Set<GroupData> all() {
      Set<GroupData> groupsSet = new HashSet<>();
      List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
      for (WebElement element : elements){
         String name = element.getText();
         int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
         groupsSet.add(new GroupData().withId(id).withName(name));
      }
      return groupsSet;
   }
    */
}

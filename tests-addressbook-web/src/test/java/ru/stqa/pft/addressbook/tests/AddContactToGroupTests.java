package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class AddContactToGroupTests extends TestBase{

   @Test
   public void testAddContactToGroup () {
      app.goTo().homePage();
   }
}

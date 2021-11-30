package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

   @Test
   public void testContactDeletion() {
      //contact in the row = 1 will be deleted

      app.getContactHelper().selectContact();
      app.getContactHelper().deleteContact();

   }
}

package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import java.io.IOException;
import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase{

   @Test
   public void testLogin1() throws IOException {
      HttpSession session = app.newSession();
      assertTrue(session.login("administrator", "root"));
      assertTrue(session.isLoggedInAs("administrator"));
   }
}

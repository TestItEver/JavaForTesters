package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;

public class RegistrationHelper {
   private final ApplicationManager app;
   private WebDriver wd;

   public RegistrationHelper(ApplicationManager app) {
      this.app = app;
      wd = app.getDriver();      // init driver only after real first call
   }

   public void start(String username, String email) {
      wd.get(app.getProperty("web.baseURL") + "/signup_page.php");
   }
}

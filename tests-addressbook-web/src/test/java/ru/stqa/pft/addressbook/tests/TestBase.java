package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

public class TestBase {

   // protected static final ApplicationManager app = new ApplicationManager(Browser.CHROME);
   protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
   protected WebDriver wd;

   @BeforeSuite(alwaysRun = true)
   public void setUp() throws Exception {
      app.init();
   }

   @AfterSuite(alwaysRun = true)
   public void tearDown() throws Exception {
      app.session().logout();
      app.stop();
   }

}

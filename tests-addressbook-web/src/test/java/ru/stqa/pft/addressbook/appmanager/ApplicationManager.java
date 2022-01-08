package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

   private final Properties properties;
   public WebDriver wd;

   private ContactHelper contactHelper;
   private NavigationHelper navigationHelper;
   private GroupHelper groupHelper;
   private SessionHelper sessionHelper;
   String browserType;
   private DbHelper dbHelper;

   public ApplicationManager(String browser) {
      this.browserType = browser;
      properties = new Properties();
   }

   public void init() throws IOException {
      String target = System.getProperty("target", "local");
      properties.load(new FileReader(new File(String.format("./src/test/resources/%s.properties", target))));

      dbHelper = new DbHelper();

      if (browserType.equals(BrowserType.FIREFOX)) {
         wd = new FirefoxDriver();
      } else if (browserType.equals(BrowserType.CHROME)) {
         wd = new ChromeDriver();
      } else if (browserType.equals(BrowserType.IE)) {
         wd = new InternetExplorerDriver();
      } else if (browserType.equals(BrowserType.EDGE)) {
         wd = new EdgeDriver();
      }

      wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
      wd.get(properties.getProperty("web.baseURL"));      // "http://localhost/addressbook/";
      groupHelper = new GroupHelper(wd);
      contactHelper = new ContactHelper(wd);
      navigationHelper = new NavigationHelper(wd);
      sessionHelper = new SessionHelper(wd);
      sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));   // ("admin", "secret")
   }

   public void stop() {
      wd.quit();
   }

   public GroupHelper group() {
      return groupHelper;
   }

   public NavigationHelper goTo() {
      return navigationHelper;
   }

   public SessionHelper session() {
      return sessionHelper;
   }

   public ContactHelper contact() {
      return contactHelper;
   }

   public DbHelper db() {return dbHelper;}
}

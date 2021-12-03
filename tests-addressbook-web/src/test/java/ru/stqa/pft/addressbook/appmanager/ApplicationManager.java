package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import javax.sql.rowset.BaseRowSet;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

   public WebDriver wd;

   private ContactHelper contactHelper;
   private NavigationHelper navigationHelper;
   private GroupHelper groupHelper;
   private SessionHelper sessionHelper;
   String browser;

   public ApplicationManager(String browser) {
      this.browser = browser;
   }

   public void init() {
      if (browser.equals(BrowserType.FIREFOX)) {
         wd = new FirefoxDriver();
      } else if (browser.equals(BrowserType.CHROME)) {
         wd = new ChromeDriver();
      } else if (browser.equals(BrowserType.IE)) {
         wd = new InternetExplorerDriver();
      }

      wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      wd.get("http://localhost/addressbook/");
      groupHelper = new GroupHelper(wd);
      contactHelper = new ContactHelper(wd);
      navigationHelper = new NavigationHelper(wd);
      sessionHelper = new SessionHelper(wd);
      sessionHelper.login("admin", "secret");
   }

   public void stop() {
      wd.quit();
   }

   public GroupHelper getGroupHelper() {
      return groupHelper;
   }

   public NavigationHelper getNavigationHelper() {
      return navigationHelper;
   }

   public SessionHelper getSessionHelper() {
      return sessionHelper;
   }

   public ContactHelper getContactHelper() {
      return contactHelper;
   }
}

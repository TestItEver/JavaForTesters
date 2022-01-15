package ru.stqa.pft.mantis.appmanager;

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
import java.util.regex.MatchResult;

public class ApplicationManager {

   private final Properties properties;
   private WebDriver wd;

   String browserType;
   private RegistrationHelper registrationHelper;

   public ApplicationManager(String browser) {
      this.browserType = browser;
      properties = new Properties();
   }

   public void init() throws IOException {
      String target = System.getProperty("target", "local");
      properties.load(new FileReader(new File(String.format("./src/test/resources/%s.properties", target))));
   }

   public void stop() {
      if (wd != null) {
         wd.quit();
      }
   }

   public HttpSession newSession() {
      return new HttpSession(this);
   }

   public String getProperty(String key) {
      return properties.getProperty(key);
   }

   public RegistrationHelper registration() {
      if (registrationHelper == null) {                           // lazy initialization (only if necessary)
         registrationHelper = new RegistrationHelper(this);
      }
      return registrationHelper;
   }

   public WebDriver getDriver() {
      if (wd == null) {                                        // lazy initialization (only if necessary)
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
      }
      return wd;
   }
}

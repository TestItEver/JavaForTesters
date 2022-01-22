package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Arrays;

public class TestBase {

   Logger logger = LoggerFactory.getLogger(TestBase.class);

   // protected static final ApplicationManager app = new ApplicationManager(Browser.CHROME);
   protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
   protected WebDriver wd;

   @BeforeSuite(alwaysRun = true)
   public void setUp() throws Exception {
      app.init();
      app.ftp().upload(new File("src/test/resources/config_inc.php"), "config/config_inc.php", "config/config_inc.php.bak");
   }

   @BeforeMethod
   public void logTestStart(Method m, Object[] p) {
      logger.info("Start " + m.getName() + " with parameter " + Arrays.asList(p));
   }

   public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException {
      if (isIssueOpen(issueId)) {
         throw new SkipException("Ignored because of issue " + issueId);
      }
   }

   private boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
      String issueStatus = app.soap().getIssueStatus(issueId);
      if (issueStatus.equals("resolved") || issueStatus.equals("closed")) {
         return false;
      }
      return true;
   }

   @AfterMethod(alwaysRun = true)
   public void logTestStop(Method m) {
      logger.info("Stop " + m.getName());
   }

   @AfterSuite(alwaysRun = true)
   public void tearDown() throws Exception {
      app.ftp().restore("config/config_inc.php.bak", "config/config_inc.php");
      app.stop();
   }

}

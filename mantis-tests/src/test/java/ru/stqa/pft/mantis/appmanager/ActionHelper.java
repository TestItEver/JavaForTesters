package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ActionHelper extends HelperBase{

   public ActionHelper(ApplicationManager app) {
      super(app);
   }

   public void login(String username, String password) {
      wd.get(app.getProperty("web.baseURL") + "/login_page.php");
      type(By.name("username"), username);
      click(By.cssSelector("input[value='Login']"));
      type(By.name("password"), password);
      click(By.cssSelector("input[value='Login']"));
   }

   public void resetPassword(String username) {
      click(By.xpath("//div[@id='sidebar']/ul/li[6]/a/i"));
      click(By.linkText("Manage Users"));
      click(By.linkText(username));
      click(By.cssSelector("input[value='Reset Password']"));
   }

}

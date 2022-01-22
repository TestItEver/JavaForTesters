package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase{

   @BeforeMethod
   public void startMailServer() {
      app.mail().start();
   }

   // precondition: at least one user <> administrator is available in the database!
   @Test
   public void testChangeUserPassword() throws MessagingException, IOException {
      Users users = app.db().users();

      UserData user = null;
      for (UserData u : users) {                                           // choose a user randomly from the database
         if (!u.getUsername().equals("administrator")) {                   // if user == administrator, then choose another user
            user = u;
            break;
         }
      }
      String newPassword = user.getPassword() + System.currentTimeMillis();          // set new password string

      app.action().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));     // log in as administrator
      app.action().resetPassword(user.getUsername());
      List<MailMessage> mailMessages = app.mail().waitForMail(1, 60000);
      String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
      app.registration().finish(confirmationLink, user.getUsername(), newPassword);

      assertTrue(app.newSession().login(user.getUsername(), newPassword));
   }

   private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
      MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
      VerbalExpression reg = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
      return reg.getText(mailMessage.text);
   }

   @AfterMethod(alwaysRun = true)
   public void stopMailServer() {
      app.mail().stop();
   }

}

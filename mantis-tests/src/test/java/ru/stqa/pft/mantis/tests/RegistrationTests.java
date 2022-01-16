package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

   @BeforeMethod
   public void startMailServer() {
      app.mail().start();
   }

   @Test
   public void testRegistration() throws MessagingException, IOException {
      long now = System.currentTimeMillis();
      String user = String.format("user%s", now);
      String email = String.format("user%s@localhost.localdomain", now);
      String password = "password";
      app.registration().start(user, email);                                           // 1. step: submit name and e-mail
      List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);  // 2. step: wait for e-mail with a link for confirmation
      String confirmationLink = findConfirmationLink(mailMessages, email);             // 3. step: extract confirmation link from the e-mail
      app.registration().finish(confirmationLink, user, password);                     // 4. step: click on the confirmation link and submit a password

      assertTrue(app.newSession().login(user, password));
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

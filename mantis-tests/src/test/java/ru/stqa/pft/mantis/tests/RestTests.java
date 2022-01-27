package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

import java.io.IOException;

public class RestTests extends TestBase{

   @Test
   public void testIssueStateRest() throws IOException {
      int issueId = 1833;
      skipIfNotFixedRest(issueId);
   }

}

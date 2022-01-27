package ru.stqa.pft.mantis.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import ru.stqa.pft.mantis.model.BugifyIssue;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class RestHelper {

   private final ApplicationManager app;

   public RestHelper(ApplicationManager app) {
      this.app = app;
   }

   public List<BugifyIssue> getIssues(int id) throws IOException {
      String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues/" + id +".json"))
              .returnContent().asString();
      JsonElement parsed = new JsonParser().parse(json);
      JsonElement issues = parsed.getAsJsonObject().get("issues");
      return new Gson().fromJson(issues, new TypeToken<List<BugifyIssue>>(){}.getType());
   }
/*
   public BugifyIssue getIssueById(int id) throws IOException {
      String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues/" + id +".json"))
              .returnContent().asString();
      JsonElement parsed = new JsonParser().parse(json);
      JsonElement issues = parsed.getAsJsonObject().get("issues");
      return new Gson().fromJson(issues, new TypeToken<BugifyIssue>(){}.getType());
   }
*/

   public Executor getExecutor() {
      return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
   }

}

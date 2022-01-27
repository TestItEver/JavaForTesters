package ru.stqa.pft.mantis.model;

public class BugifyIssue {

   private int id;
   private String subject;
   private String description;
   private String state_name;

   public int getId() {
      return id;
   }

   public BugifyIssue withId(int id) {
      this.id = id;
      return this;
   }

   public String getSubject() {
      return subject;
   }

   public BugifyIssue withSubject(String subject) {
      this.subject = subject;
      return this;
   }

   public String getDescription() {
      return description;
   }

   public BugifyIssue withDescription(String description) {
      this.description = description;
      return this;
   }

   public String getState_name() {
      return state_name;
   }

   public BugifyIssue withState_name(String state_name) {
      this.state_name = state_name;
      return this;
   }

   @Override
   public String toString() {
      return "BugifyIssue{" +
              "id=" + id +
              ", subject='" + subject + '\'' +
              ", description='" + description + '\'' +
              ", state_name='" + state_name + '\'' +
              '}';
   }
}

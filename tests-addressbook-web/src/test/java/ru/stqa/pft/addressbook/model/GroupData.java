package ru.stqa.pft.addressbook.model;

public class GroupData {
   private final String groupname;
   private final String groupheader;
   private final String groupfooter;

   public GroupData(String groupname, String groupheader, String groupfooter) {
      this.groupname = groupname;
      this.groupheader = groupheader;
      this.groupfooter = groupfooter;
   }

   public String getGroupname() {
      return groupname;
   }

   public String getGroupheader() {
      return groupheader;
   }

   public String getGroupfooter() {
      return groupfooter;
   }
}

package ru.stqa.pft.addressbook.model;

import java.util.HashSet;
import java.util.Set;

public class MySetWrapper extends HashSet<GroupData> {
   private Set<GroupData> mySet;

   public MySetWrapper() {
      this.mySet = new HashSet<GroupData>();
   }

   public MySetWrapper withAdded(MySetWrapper set, GroupData group) {
      set.add(group);
      return set;
   }

}

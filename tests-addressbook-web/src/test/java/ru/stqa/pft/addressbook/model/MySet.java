package ru.stqa.pft.addressbook.model;

import java.util.HashSet;

public class MySet extends HashSet<GroupData> {

   public MySet withAdded(MySet set, GroupData group) {
      set.add(group);
      return set;
   }

}

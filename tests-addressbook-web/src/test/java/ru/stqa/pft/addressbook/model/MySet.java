package ru.stqa.pft.addressbook.model;

import java.util.HashSet;

// we could also extend HashSet directly (instead of ForwardingSet) but it is not recommended!!!
// (could lead to some problems)

public class MySet extends HashSet<GroupData> {

   public MySet withAdded(MySet set, GroupData group) {
      set.add(group);
      return set;
   }

}

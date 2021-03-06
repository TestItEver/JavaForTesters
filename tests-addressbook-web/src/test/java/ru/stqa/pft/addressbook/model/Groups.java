package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Groups extends ForwardingSet<GroupData> {
   //This is a wrapper for sets implementation

   private Set<GroupData> delegate;

   public Groups(Collection<GroupData> groups) {
      this.delegate = new HashSet<GroupData>(groups);
   }
   public Groups(Groups groups) {
      this.delegate = new HashSet<GroupData>(groups.delegate);   // create copy from group set
   }
   public Groups() {
      this.delegate = new HashSet<GroupData>();
   }

   @Override
   protected Set delegate() {
      return delegate;
   }

   public Groups withAdded(GroupData group) {
      Groups groups = new Groups(this);
      groups.add(group);
      return groups;
   }

   public Groups without(GroupData group) {
      Groups groups = new Groups(this);
      groups.remove(group);
      return groups;
   }

   public Groups withModified(int id, GroupData group) {
      Groups groups = new Groups(this);
      for (GroupData g : groups) {
         if (g.getId() == id) {
            groups.remove(g);
            groups.add(group);
            break;
         }
      }
      return groups;
   }

}

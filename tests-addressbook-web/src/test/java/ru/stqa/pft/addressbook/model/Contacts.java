package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {
   //This is a wrapper for sets implementation

   private Set<ContactData> delegate = new HashSet<ContactData>();

   @Override
   protected Set<ContactData> delegate() {
      return null;
   }
}

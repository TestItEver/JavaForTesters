package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {
   //This is a wrapper for sets implementation

   private Set<ContactData> delegate;

   public Contacts(Contacts contacts) {
      this.delegate = new HashSet<ContactData>(contacts.delegate);   // create copy from contact set
   }

   public Contacts() {
      this.delegate = new HashSet<ContactData>();
   }

   @Override
   protected Set delegate() {
      return delegate;
   }

   public Contacts withAdded(ContactData contact) {
      Contacts contacts = new Contacts(this);
      contacts.add(contact);
      return contacts;
   }

   public Contacts without(ContactData contact) {
      Contacts contacts = new Contacts(this);
      contacts.remove(contact);
      return contacts;
   }

   public Contacts withModified(int id, ContactData contact) {
      Contacts contacts = new Contacts(this);
      for (ContactData c : contacts) {
         if (c.getId() == id) {
            contacts.remove(c);
            contacts.add(contact);
            break;
         }
      }
      return contacts;
   }
}

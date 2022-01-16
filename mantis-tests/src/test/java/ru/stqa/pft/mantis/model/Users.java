package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Users extends ForwardingSet<UserData> {
   //This is a wrapper for sets implementation

   private Set<UserData> delegate;

   public Users(Collection<UserData> users) {
      this.delegate = new HashSet<UserData>(users);
   }
   public Users(Users users) {
      this.delegate = new HashSet<UserData>(users.delegate);   // create copy from contact set
   }
   public Users() {
      this.delegate = new HashSet<UserData>();
   }

   @Override
   protected Set delegate() {
      return delegate;
   }

}

package ru.stqa.pft.addressbook.model;

public class ContactData {
   private final String firstname;
   private final String lastname;
   private final String company;
   private final String mobile;
   private final String bday;
   private final String bmonth;
   private final String byear;
   private final String email;
   private final String group;

   public ContactData(String firstname, String lastname, String company, String mobile, String bday, String bmonth, String byear, String email, String group) {
      this.firstname = firstname;
      this.lastname = lastname;
      this.company = company;
      this.mobile = mobile;
      this.bday = bday;
      this.bmonth = bmonth;
      this.byear = byear;
      this.email = email;
      this.group = group;
   }

   public String getFirstname() {
      return firstname;
   }

   public String getLastname() {
      return lastname;
   }

   public String getCompany() {
      return company;
   }

   public String getMobile() {
      return mobile;
   }

   public String getBday() {
      return bday;
   }

   public String getBmonth() {
      return bmonth;
   }

   public String getByear() {
      return byear;
   }

   public String getEmail() {
      return email;
   }

   public String getGroup() {
      return group;
   }
}

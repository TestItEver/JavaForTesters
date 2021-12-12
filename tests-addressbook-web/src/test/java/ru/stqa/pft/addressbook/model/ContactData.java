package ru.stqa.pft.addressbook.model;

public class ContactData {
   private int id;
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
      this.id = 0;
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

   public ContactData(int id, String firstname, String lastname, String company, String mobile, String bday, String bmonth, String byear, String email, String group) {
      this.id = id;
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

   @Override
   public String toString() {
      return "ContactData{" +
              "firstname='" + firstname + '\'' +
              ", lastname='" + lastname + '\'' +
              ", mobile='" + mobile + '\'' +
              '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      ContactData that = (ContactData) o;

      if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
      if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
      return mobile != null ? mobile.equals(that.mobile) : that.mobile == null;
   }

   @Override
   public int hashCode() {
      int result = firstname != null ? firstname.hashCode() : 0;
      result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
      result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
      return result;
   }
}

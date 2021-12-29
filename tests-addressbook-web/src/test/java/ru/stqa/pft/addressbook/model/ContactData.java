package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;

public class ContactData {
   @XStreamOmitField
   private int id = Integer.MAX_VALUE;
   @Expose
   private String firstname;
   @Expose
   private String lastname;
   @Expose
   private String company;
   private String address;
   private String homePhone;
   @Expose
   private String mobilePhone;
   private String workPhone;
   @Expose
   private String email;
   private String email2;
   private String email3;
   @Expose
   private String bday;
   @Expose
   private String bmonth;
   @Expose
   private String byear;
   private File photo;
   @Expose
   private String group;
   private String allEmails;
   private String allPhones;


   public ContactData withId(int id) {
      this.id = id;
      return this;
   }

   public ContactData withFirstname(String firstname) {
      this.firstname = firstname;
      return this;
   }

   public ContactData withLastname(String lastname) {
      this.lastname = lastname;
      return this;
   }

   public ContactData withCompany(String company) {
      this.company = company;
      return this;
   }

   public ContactData withAddress(String address) {
      this.address = address;
      return this;
   }

   public ContactData withHomePhone(String homePhone) {
      this.homePhone = homePhone;
      return this;
   }

   public ContactData withMobilePhone(String mobile) {
      this.mobilePhone = mobile;
      return this;
   }

   public ContactData withWorkPhone(String workPhone) {
      this.workPhone = workPhone;
      return this;
   }

   public ContactData withEmail(String email) {
      this.email = email;
      return this;
   }

   public ContactData withEmail2(String email2) {
      this.email2 = email2;
      return this;
   }

   public ContactData withEmail3(String email3) {
      this.email3 = email3;
      return this;
   }

   public ContactData withBday(String bday) {
      this.bday = bday;
      return this;
   }

   public ContactData withBmonth(String bmonth) {
      this.bmonth = bmonth;
      return this;
   }

   public ContactData withByear(String byear) {
      this.byear = byear;
      return this;
   }

   public ContactData withPhoto(File photo) {
      this.photo = photo;
      return this;
   }

   public ContactData withGroup(String group) {
      this.group = group;
      return this;
   }

   public ContactData withAllEmails(String allEmails) {
      this.allEmails = allEmails;
      return this;
   }

   public ContactData withAllPhones(String allPhones) {
      this.allPhones = allPhones;
      return this;
   }

   public int getId() {
      return id;
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

   public String getAddress() {
      return address;
   }

   public String getHomePhone() {
      return homePhone;
   }

   public String getMobilePhone() {
      return mobilePhone;
   }

   public String getWorkPhone() {
      return workPhone;
   }

   public String getEmail() {
      return email;
   }

   public String getEmail2() {
      return email2;
   }

   public String getEmail3() {
      return email3;
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

   public File getPhoto() {
      return photo;
   }

   public String getGroup() {
      return group;
   }

   public String getAllEmails() {
      return allEmails;
   }

   public String getAllPhones() {
      return allPhones;
   }


   @Override
   public String toString() {
      return "ContactData{" +
              "firstname='" + firstname + '\'' +
              ", lastname='" + lastname + '\'' +
              ", mobile='" + mobilePhone + '\'' +
              '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      ContactData that = (ContactData) o;

      if (id != that.id) return false;
      if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
      return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
   }

   @Override
   public int hashCode() {
      int result = id;
      result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
      result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
      return result;
   }
}

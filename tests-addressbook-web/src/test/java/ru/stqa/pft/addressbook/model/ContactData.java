package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@Entity
@Table(name = "addressbook")
public class ContactData {
   @XStreamOmitField
   @Id
   @Column(name = "id")
   private int id = Integer.MAX_VALUE;

   @Expose
   @Column(name = "firstname")
   private String firstname;

   @Expose
   @Column(name = "lastname")
   private String lastname;

   @Expose
   @Column(name = "company")
   private String company;

   @Column(name = "address")
   @Type(type = "text")
   private String address;

   @Column(name = "home")
   @Type(type = "text")
   private String homePhone;

   @Expose
   @Column(name = "mobile")
   @Type(type = "text")
   private String mobilePhone;

   @Column(name = "work")
   @Type(type = "text")
   private String workPhone;

   @Expose
   @Type(type = "text")
   private String email;
   @Type(type = "text")
   private String email2;
   @Type(type = "text")
   private String email3;

   @Expose
   // @Column(name = "bday")
   // @Type(type = "byte")
   @Transient
   private String bday;

   @Expose
   @Column(name = "bmonth")
   private String bmonth;

   @Expose
   @Column(name = "byear")
   private String byear;

   @Column(name = "photo")
   @Type(type = "text")
   private String photo;

   @Expose
   @Transient
   private String group;

   @Transient
   private String allEmails;
   @Transient
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
      this.photo = photo.getPath();
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
      return (bday);
   }

   public String getBmonth() {
      return bmonth;
   }

   public String getByear() {
      return byear;
   }

   public File getPhoto() {
      return new File(photo);
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

package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import net.bytebuddy.build.Plugin;
import org.hibernate.annotations.Type;
import org.hibernate.type.descriptor.java.UUIDTypeDescriptor;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

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
   @Column(name = "bday")
   @Type(type = "byte")
   // @Transient
   private Byte bday;

   @Expose
   @Column(name = "bmonth")
   private String bmonth;

   @Expose
   @Column(name = "byear")
   private String byear;

   @Column(name = "photo")
   @Type(type = "text")
   private String photo;

   //@Expose
   //private String group;
   @ManyToMany(fetch = FetchType.EAGER)     // FetchType.EAGER = all information about contact should be load at once
   @JoinTable(name = "address_in_groups",   // Description of the relationship between contacts and groups
           joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
   private Set<GroupData> groups = new HashSet<GroupData>();

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
      this.bday = Byte.parseByte(bday);
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

   public ContactData inGroup(GroupData group) {
      groups.add(group);
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

   public ContactData removeGroup(GroupData group) {
      groups.remove(group);
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
      return Byte.toString(bday);
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

   public Groups getGroups() {
      return new Groups(groups);
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
              ", bday='" + bday + '\'' +
              ", bmonth='" + bmonth + '\'' +
              ", byear='" + byear + '\'' +
              ", groups='" + groups + '\'' +
              '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      ContactData that = (ContactData) o;

      if (id != that.id) return false;
      if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
      if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
      if (bday != null ? !bday.equals(that.bday) : that.bday != null) return false;
      if (bmonth != null ? !bmonth.equals(that.bmonth) : that.bmonth != null) return false;
      if (byear != null ? !byear.equals(that.byear) : that.byear != null) return false;
      return groups != null ? groups.equals(that.groups) : that.groups == null;
   }

   @Override
   public int hashCode() {
      int result = id;
      result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
      result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
      result = 31 * result + (bday != null ? bday.hashCode() : 0);
      result = 31 * result + (bmonth != null ? bmonth.hashCode() : 0);
      result = 31 * result + (byear != null ? byear.hashCode() : 0);
      result = 31 * result + (groups != null ? groups.hashCode() : 0);
      return result;
   }

}

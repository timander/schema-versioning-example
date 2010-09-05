package net.timandersen.model;

import java.util.HashSet;
import java.util.Set;

public class Person {
  private Long id;
  private String addressLine1;
  private String addressLine2;
  private String city;
  private String firstName;
  private String lastName;
  private String state;
  private String zipCode;
  private Set<Phone> phones = new HashSet<Phone>();
  private Set<Email> emails = new HashSet<Email>();

  public String getAddressLine1() {
    return addressLine1;
  }

  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  public String getAddressLine2() {
    return addressLine2;
  }

  public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Set<Email> getEmails() {
    return emails;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Long getId() {
    return id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Set<Phone> getPhones() {
    return phones;
  }

  public void setPhones(Set<Phone> phones) {
    this.phones = phones;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public void addPhone(Phone phone) {
    phones.add(phone);
  }

  public void addEmail(Email email) {
    emails.add(email);
  }

}

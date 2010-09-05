package net.timandersen.model;

public class Email {

  private Long id;
  private String address;
  private String type;
  private Person person;

  public Email(String address, String type, Person person) {
    this.address = address;
    this.type = type;
    this.person = person;
  }

  public String getAddress() {
    return address;
  }

  public String getType() {
    return type;
  }

  public Person getPerson() {
    return person;
  }

}

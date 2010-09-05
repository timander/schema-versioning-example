package net.timandersen.model;

public class Phone {
  
  private Long id;
  private String number;
  private String type;
  private Person person;

  public Phone(String number, String type, Person person) {
    this.number = number;
    this.type = type;
    this.person = person;
  }

  public Long getId() {
    return id;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}


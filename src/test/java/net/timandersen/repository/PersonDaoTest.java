package net.timandersen.repository;

import net.timandersen.model.Email;
import net.timandersen.model.Person;
import net.timandersen.model.Phone;
import net.timandersen.util.SpringContextWrapper;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertTrue;


public class PersonDaoTest {

  @Test
  public void saveFindDelete() {
    Person person = new Person();
    person.setFirstName("Sam");
    person.setLastName("Jones");

    person.addPhone(new Phone("555-555-5555", "H", person));
    person.addEmail(new Email("me@there.com", "P", person));

    personDao().save(person);
    List<Person> people = personDao().findAll();

    boolean found = false;
    for (Person sam : people) {
      if ("Jones".equals(sam.getLastName()) && "Sam".equals(sam.getFirstName())) {
        found = true;
      }
    }
    assertTrue(found);
    personDao().delete(person);
  }


  private PersonDao personDao() {
    return SpringContextWrapper.getBean("personDao", PersonDao.class);
  }
}

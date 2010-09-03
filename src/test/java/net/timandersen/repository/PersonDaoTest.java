package net.timandersen.repository;

import net.timandersen.model.Person;
import net.timandersen.util.SpringContextWrapper;
import org.junit.Test;
import java.util.List;

import static junit.framework.Assert.*;


public class PersonDaoTest {

    @Test
    public void saveFindDelete() {
        Person person = new Person();
        person.setFirstName("Sam");
        person.setLastName("Jones");
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

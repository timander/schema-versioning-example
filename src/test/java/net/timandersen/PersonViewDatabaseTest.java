package net.timandersen;

import net.timandersen.util.SpringContextWrapper;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.*;


public class PersonViewDatabaseTest {

    @Test
    public void backwardsCompatibility() {
        PersonViewResultSetExtractor personView = new PersonViewResultSetExtractor();
        List<PersonView> people = SpringContextWrapper.getBean("jdbcTemplate", JdbcTemplate.class)
                .query("select * from people_view", personView);

        assertEquals(2, people.size());

        assertEquals(
                "PersonView{person_id='1', first_name='John', last_name='Doe', phone_number='555-111-1111', address_line_1='123 Main', address_line_2='Apt #2', city='Des Moines', state='IA', zip_code='50265'}",
                people.get(0).toString());

        assertEquals(
                "PersonView{person_id='2', first_name='Jane', last_name='Smith', phone_number='555-222-2222', address_line_1='789 Spruce', address_line_2='null', city='De Soto', state='IA', zip_code='50069'}",
                people.get(1).toString());
    }


    private static class PersonViewResultSetExtractor implements ResultSetExtractor<List<PersonView>> {

        @Override
        public List<PersonView> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            List<PersonView> results = new ArrayList<PersonView>();
            while (resultSet.next()) {
                PersonView personView = new PersonView();
                personView.person_id = resultSet.getString("person_id");
                personView.first_name = resultSet.getString("first_name");
                personView.last_name = resultSet.getString("last_name");
                personView.phone_number = resultSet.getString("phone_number");
                personView.address_line_1 = resultSet.getString("address_line_1");
                personView.address_line_2 = resultSet.getString("address_line_2");
                personView.city = resultSet.getString("city");
                personView.state = resultSet.getString("state");
                personView.zip_code = resultSet.getString("zip_code");
                results.add(personView);
            }
            return results;
        }


        private void extract(ResultSet resultSet, Map<String, String> map, String columnLabel) throws SQLException {
            map.put(columnLabel, resultSet.getString(columnLabel));
        }

    }

    private static class PersonView {
        private String person_id;
        private String first_name;
        private String last_name;
        private String phone_number;
        private String address_line_1;
        private String address_line_2;
        private String city;
        private String state;
        private String zip_code;

        @Override
        public String toString() {
            return "PersonView{" +
                   "person_id='" + person_id + '\'' +
                   ", first_name='" + first_name + '\'' +
                   ", last_name='" + last_name + '\'' +
                   ", phone_number='" + phone_number + '\'' +
                   ", address_line_1='" + address_line_1 + '\'' +
                   ", address_line_2='" + address_line_2 + '\'' +
                   ", city='" + city + '\'' +
                   ", state='" + state + '\'' +
                   ", zip_code='" + zip_code + '\'' +
                   '}';
        }
    }

}

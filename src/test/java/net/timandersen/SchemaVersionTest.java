package net.timandersen;

import net.timandersen.util.SpringContextWrapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.io.File;


public class SchemaVersionTest extends SchemaVersionTestCase {


    @Override
    public JdbcTemplate jdbcTemplate() {
        return SpringContextWrapper.getBean("jdbcTemplate", JdbcTemplate.class);
    }


    @Override
    public File sqlFolder() {
        File directory = new File("schema-versioning-example/src/main/sql/changes");
        if (!directory.isDirectory()) {
            directory = new File("src/main/sql/changes");
        }
        return directory;

    }
}

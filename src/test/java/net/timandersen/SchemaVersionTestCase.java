package net.timandersen;

import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public abstract class SchemaVersionTestCase extends TestCase {

    public void testSchemaVersionMetadata() throws Exception {
        ResultSetExtractor rse = new ResultSetExtractor() {
            public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
                ResultSetMetaData metaData = rs.getMetaData();

                int rowCount = 4;
                assertEquals(rowCount, metaData.getColumnCount());

                assertEquals("schema_version_id", metaData.getColumnName(1));
                assertEquals(Types.INTEGER, metaData.getColumnType(1));
                assertEquals(11, metaData.getColumnDisplaySize(1));

                assertEquals("script_name", metaData.getColumnName(2));
                assertEquals(Types.VARCHAR, metaData.getColumnType(2));
                assertEquals(255, metaData.getColumnDisplaySize(2));

                assertEquals("username", metaData.getColumnName(3));
                assertEquals(Types.VARCHAR, metaData.getColumnType(3));
                assertEquals(32, metaData.getColumnDisplaySize(3));

                assertEquals("date_applied", metaData.getColumnName(4));
                assertEquals(Types.TIMESTAMP, metaData.getColumnType(4));

                return null;
            }
        };
        jdbcTemplate().query("select schema_version_id, script_name, username, date_applied from schema_version", rse);
    }


    public void testIncludeSchemaVersionInSqlFiles() throws IOException {
        List<String> filesystemScripts = scriptsOnFileSystem();
        List<String> appliedScripts = scriptsInDatabase();

        List<String> errors = new ArrayList<String>();

        for (String script : filesystemScripts) {
            if (!appliedScripts.contains(script)) {
                String copyMe = "insert into schema_version (script_name) values ('" + script + "')\n\n";
                errors.add("\nscript: " + script + " does not communicate the schema version\ntry adding:\n" + copyMe);
            }
        }

        for (String script : appliedScripts) {
            if (!filesystemScripts.contains(script)) {
                errors.add("\nscript: " + script + " is missing on the file system, but exists in the schema_version table\n");
            }
        }
        assertEquals(errors.toString(), 0L, (long) errors.size());
    }


    public abstract JdbcTemplate jdbcTemplate();

    public List<File> listFiles(File dir, boolean recursive, String... extension) {
        return new ArrayList<File>(FileUtils.listFiles(dir, extension, recursive));
    }


    public abstract File sqlFolder();

    private List<String> scriptsInDatabase() throws IOException {
        List<String> appliedScripts = new ArrayList<String>();
        JdbcTemplate jdbcTemplate = jdbcTemplate();

        for (Map map : jdbcTemplate.queryForList("select script_name from schema_version")) {
            appliedScripts.add((String) map.get("script_name"));
        }

        return appliedScripts;
    }


    private List<String> scriptsOnFileSystem() {
        File directory = sqlFolder();

        List<File> files = listFiles(directory, true, "sql");

        List<String> scripts = new ArrayList<String>();
        for (File file : files) {
            scripts.add(file.getName());
        }
        return scripts;
    }
}

package ua.training.util;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class WagonJDBCServletTest {
    private static final String DRIVER_STRING = "com.mysql.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/transport?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "12051989";
    private static Connection conn;

    @BeforeClass
    public static void initJdbc() {

        try {
            Class.forName(DRIVER_STRING);
            conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }


        try {
            Statement stmt = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS wagontest (\n" +
                    "  id             INT          NOT NULL PRIMARY KEY,\n" +
                    "  wagon_type     ENUM('COMMON', 'COUCHETTE', 'COMPARTMENT', 'BUSINESS') NOT NULL,\n" +
                    "  numberofpassengers  INT NOT NULL,\n" +
                    "  amountofluggage     INT NOT NULL\n" +
                    ")";

            stmt.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @AfterClass
    public static void teardownJdbc() {

        // Delete table wagon
        try {
            conn.createStatement().execute("DROP TABLE wagontest");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Connection conclude
        try {
            if (conn != null || !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void dml() {

        // Insert data
        int countInserts = 0;
        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO wagontest VALUE (4, 'COMMON', 23, 20);";
            countInserts += stmt.executeUpdate(sql);
            sql = "INSERT INTO wagontest VALUE (5, 'BUSINESS', 6, 5)";
            countInserts += stmt.executeUpdate(sql);
            sql = "INSERT INTO wagontest VALUE (6, 'COUCHETTE', 6, 5)";
            countInserts += stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertThat(countInserts, is(3));

        // query data
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT  * FROM wagontest");
            ResultSet rs = pstmt.executeQuery();

            rs.next();

            assertThat(rs.getString("wagon_type"), is("COMMON"));
            assertThat(rs.getInt("numberofpassengers"), is(23));
            rs.next();

            assertThat(rs.getString("wagon_type"), is("BUSINESS"));
            assertThat(rs.getInt("numberofpassengers"), is(6));
            rs.next();

            assertThat(rs.getString("wagon_type"), is("COUCHETTE"));
            assertThat(rs.getInt("numberofpassengers"), is(6));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
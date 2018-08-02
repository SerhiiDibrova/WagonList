package ua.training.util;

import ua.training.model.Wagon;
import ua.training.model.WagonComfortType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * created by Dibrova Serhii
 */
public class WagonDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public WagonDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    /**
     * Specify to the DriverManager which JDBC drivers to try to make Connections with.
     * The easiest way to do this is to use Class.forName() on the class that implements the java.sql.Driver interface.
     * With MySQL Connector/J, the name of this class is com.mysql.jdbc.Driver. With this method,
     * you could use an external configuration file to supply the driver class name and driver parameters
     * to use when connecting to a database.
     *
     * @throws SQLException
     */
    private void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    /**
     * disconnect from JDBC
     *
     * @throws SQLException
     */
    private void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    /**
     * Insert Into transport.wagon
     *
     * @param wagon
     * @return
     * @throws SQLException
     */
    public boolean insertWagon(Wagon wagon) throws SQLException {
        String sql = "INSERT INTO wagon (id,wagon_type, numberofpassengers, amountofluggage) VALUES (?, ?, ?,?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, wagon.getId());
        statement.setString(2, wagon.getType().name());
        statement.setInt(3, wagon.getNumberOfPassengers());
        statement.setInt(4, wagon.getAmountOfLuggage());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    /**
     * show all list from transport.wagon
     *
     * @return
     * @throws SQLException
     */
    public List<Wagon> listAllWagons() throws SQLException {
        List<Wagon> listWagon = new ArrayList<>();

        String sql = "SELECT * FROM wagon";

        connect();
       /* A Statement is an interface that represents a SQL statement.
         You execute Statement objects, and they generate ResultSet objects, which is a table of data representing a
        database result set*/
        Statement statement = jdbcConnection.createStatement();
        //Executes the given SQL statement, which returns a single ResultSet object
        ResultSet resultSet = statement.executeQuery(sql);

        resultSet(listWagon, resultSet);

        resultSet.close();
        statement.close();

        disconnect();
        List sortedByType = listWagon.stream()
                .sorted((s1, s2) -> {
                    return s1.getType().compareTo(s2.getType());
                }).collect(Collectors.toList());
        return sortedByType;
    }

    /**
     * Executes the given SQL statement, which returns a single ResultSet object and added objects in list
     *
     * @param listWagon
     * @param resultSet
     * @throws SQLException
     */
    private void resultSet(List<Wagon> listWagon, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int number = resultSet.getInt("id");
            WagonComfortType type = WagonComfortType.valueOf(resultSet.getString("wagon_type"));
            int numberOfPassengers = resultSet.getInt("numberofpassengers");
            int amountOfLuggage = resultSet.getInt("amountofluggage");

            Wagon wagon = new Wagon(number, type, numberOfPassengers, amountOfLuggage);
            listWagon.add(wagon);
        }
    }

    /**
     * delete from table transport.wagon where id = ?
     *
     * @param wagon
     * @return
     * @throws SQLException
     */
    public boolean deleteWagon(Wagon wagon) throws SQLException {
        String sql = "DELETE FROM wagon where id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, wagon.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    /**
     * update from transport.wagon set type wagon_type = ?, numberofpassengers = ?, amountofluggage = ? WHERE id = ?
     *
     * @param wagon
     * @return
     * @throws SQLException
     */
    public boolean updateWagon(Wagon wagon) throws SQLException {
        String sql = "UPDATE wagon SET wagon_type = ?, numberofpassengers = ?, amountofluggage = ?";
        sql += " WHERE id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, wagon.getType().name());
        statement.setInt(2, wagon.getNumberOfPassengers());
        statement.setInt(3, wagon.getAmountOfLuggage());
        statement.setInt(4, wagon.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    /**
     * Get Wagon by ID from transport.wagon
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public Wagon getWagon(int id) throws SQLException {
        Wagon wagon = null;
        String sql = "SELECT * FROM wagon WHERE id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            WagonComfortType type = WagonComfortType.valueOf(resultSet.getString("wagon_type"));
            int numberOfPassengers = resultSet.getInt("numberofpassengers");
            int amountOfLuggage = resultSet.getInt("amountofluggage");

            wagon = new Wagon(id, type, numberOfPassengers, amountOfLuggage);
        }

        resultSet.close();
        statement.close();

        return wagon;
    }
}

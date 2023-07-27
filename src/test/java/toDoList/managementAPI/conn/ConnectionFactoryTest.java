package toDoList.managementAPI.conn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

class ConnectionFactoryTest {

    @Test
    void getConnection() {
        try (Connection connection = ConnectionFactory.getConnection()){
            Assertions.assertFalse(connection.isClosed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
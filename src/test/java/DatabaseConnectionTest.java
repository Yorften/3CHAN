import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import config.DatabaseConnection;

public class DatabaseConnectionTest {
    	private Connection connection;

	@Before
	public void setUp() {
		connection = DatabaseConnection.getConnection();
	}

	@After
	public void tearDown() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
	}

	@Test
	public void testConnectionIsNotNull() {
		assertNotNull("The connection should not be null", connection);
	}

	@Test
	public void testConnectionIsValid() throws SQLException {
		assertTrue("The connection should be valid and open", connection != null && !connection.isClosed());
	}
}

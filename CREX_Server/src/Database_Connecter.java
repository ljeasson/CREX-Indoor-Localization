import java.sql.*;

public class Database_Connecter {

	private String hostname = "";
	private String port = "";
	private String database_name = "";
	private String username = "";
	private String password = "";

	private static Connection connection;

	// Constructor (Establish connection to database using hostname, port,
	// username, and password)
	public Database_Connecter(String h, String p, String d, String u, String pass) {
		hostname = h;
		port = p;
		database_name = d;
		username = u;
		password = pass;

		Establish_Connection(hostname, port, database_name, username, password);

	}

	// Establish connection to database
	protected void Establish_Connection(String hostname, String port, String database_name, String username,
			String password) {
		
	}

	// Create a Table with specified columns
	public void Create_Table(String table_name, String column_1, String column_2, String column_3, String column_4,
			String column_5) {
		
	}

	// Insert data into specific table
	public void Insert_Data(String table_name) {
		
	}
	
	// Update data in specific table column
	public void Update_Data(String table_name) {
		
	}
	
	// Delete data from specified table
	public void Delete_Data(String table_name) {
		
	}

	public static void main(String[] args) {

		// Register JDBC Driver
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
			e.printStackTrace();
			return;
		}
		System.out.println("PostgreSQL JDBC Driver Registered!");

		// Establish connection to database
		connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/localization", "postgres",
					"StinkyFace@1");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}

		// Test Create Statement
		try {
			Statement stmt = connection.createStatement();
			String sql = "CREATE TABLE company (" + "ID       INT     PRIMARY KEY     NOT NULL" + ","
					+ "NAME     TEXT    NOT NULL" + "," + "AGE      INT     NOT NULL" + "," + "ADDRESS  CHAR(50)" + ","
					+ "SALARY   REAL" + ")";

			stmt.executeUpdate(sql);
			stmt.close();

			System.out.println("Table Created Successfully\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Test Insert Statement
		try {
			Statement stmt = connection.createStatement();
			String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
					+ "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " + "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " + "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
					+ "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
			stmt.executeUpdate(sql);

			stmt.close();

			System.out.println("Data Inserted Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Test Select Statement
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANY;");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String address = rs.getString("address");
				float salary = rs.getFloat("salary");
				System.out.println("ID = " + id);
				System.out.println("NAME = " + name);
				System.out.println("AGE = " + age);
				System.out.println("ADDRESS = " + address);
				System.out.println("SALARY = " + salary);
				System.out.println();
			}
			rs.close();
			stmt.close();

			System.out.println("Data Selection Successful");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Test Update Statement
		try {
			Statement stmt = connection.createStatement();
			String sql = "UPDATE COMPANY set SALARY = 25000.00 where ID=1;";
			stmt.executeUpdate(sql);

			ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANY;");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String address = rs.getString("address");
				float salary = rs.getFloat("salary");
				System.out.println("ID = " + id);
				System.out.println("NAME = " + name);
				System.out.println("AGE = " + age);
				System.out.println("ADDRESS = " + address);
				System.out.println("SALARY = " + salary);
				System.out.println();
			}
			rs.close();
			stmt.close();

			System.out.println("Data Update Successful");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Test Delete Statement
		try {
			Statement stmt = connection.createStatement();
			String sql = "DELETE from COMPANY where ID = 2;";
			stmt.executeUpdate(sql);

			ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANY;");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String address = rs.getString("address");
				float salary = rs.getFloat("salary");
				System.out.println("ID = " + id);
				System.out.println("NAME = " + name);
				System.out.println("AGE = " + age);
				System.out.println("ADDRESS = " + address);
				System.out.println("SALARY = " + salary);
				System.out.println();
			}
			rs.close();
			stmt.close();

			connection.close();

			System.out.println("Data Deletion Successful");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}

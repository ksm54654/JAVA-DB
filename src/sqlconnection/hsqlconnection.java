package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class hsqlconnection {

	public static void main(String[] args) {

		Connection conn = null;

		try {
			String driver = "org.h2.Driver";
			String url = "jdbc:h2:tcp://localhost/~/telephone";
			String username = "scott";
			String password = "tiger";

			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);

			System.out.println("Connection Succes");
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {

			}
		}
	}
}

package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class sqlconnection {

	public static void main(String[] args) {

		Connection conn = null;

		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/world";
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

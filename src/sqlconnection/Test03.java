package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class Test03 {

	private Connection connectDB() {

		try {
//			Class.forName("org.h2.Driver");
//			Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/telephone", "scott", "tiger");
	
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "scott", "tiger");
			
			System.out.println("데이터베이스가 연결되었습니다.");
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void closeDB(Connection con) {
		try {
			con.close();
			System.out.println("데이터베이스가 닫혔습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void insertContactWithStatement(Connection con) {

		Statement st = null;
		String[] cate = { "friend", "family", "colleague", "etc" };
		Random rd = new Random();
		int totcnt = 1000000;

		try {
			st = con.createStatement();

			for (int i = 1; i <= totcnt; i++) {
				String name = "name" + i;
				String classification = cate[rd.nextInt(4)];
				String address = "address" + i;
				String rectal = "rectal" + i;
				String birthday = String.format("%4d-%02d-%02d", rd.nextInt(1950, 2022), rd.nextInt(1, 13),
						rd.nextInt(1, 29));

				String sql = String.format("insert into contact (cid, name, classification, address, rectal, birthday)"
						+ "values (%d, '%s', '%s', '%s', '%s','%s')", i, name, classification, address, rectal, birthday);

				st.executeUpdate(sql);
				System.out.println(String.format("%.2f:%d/%d", i * 100 / (double) totcnt, i, totcnt));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (Exception e) {

			}
		}
	}

	public static void main(String[] args) {
		Test03 tt = new Test03();
		Connection con = tt.connectDB();

		if (con != null) {

				tt.insertContactWithStatement(con);

			
			tt.closeDB(con);
		}
		
	}

}

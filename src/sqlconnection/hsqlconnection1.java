package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class hsqlconnection1 {

	Connection con = null;

	private boolean connectDB() {

		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/telephone", "scott", "tiger");
			System.out.println("데이터베이스가 연결되었습니다.");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private void closeDB() {
		try {
			con.close();
			System.out.println("데이터베이스가 닫혔습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void insertDept(String dno, String dname, int budget) {

		String sql = "insert into dept (dno, dname, budget) values (?, ?, ?)";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dno);
			ps.setString(2, dname);
			ps.setInt(3, budget);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("데이터베이스가 입력되었습니다.");
	}

	public static void main(String[] args) {
		hsqlconnection1 tt = new hsqlconnection1();
		if (tt.connectDB()) {
			for (int i = 0; i < 100; i++) {
				tt.insertDept("d"+ i, "dname" + i, 1000 + i);
			}
			tt.closeDB();
		}
	}

}

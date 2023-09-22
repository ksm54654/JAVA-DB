package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

// Statement : 완결된 Query 문장을 실행할 때
// PreparedStatment : 변수로 값을 추가할 수 있는 Query문을 실행할 때

// select ==> executeQuery : PreparedStatment, Statement
// insert/delete/update ==> excuteUpdate : PreparedStatment, Statement

public class sqlconnection2 {

	Connection con = null;

	// Statement, excuteUpdate
	private void insertDeptStatement(String dno, String dname, int budget) {

		String sql = String.format("insert into dept (dno, dname, budget) values ('%s', '%s', %d)", dno, dname, budget);
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
//			int cnt = con
//					.createStatement()
//					.executeUpdate(String.format("insert into dept (dno, dname, budget) values ('%s', '%s', %d)", dno, dname, budget));

		} catch (SQLException e) {

			e.printStackTrace();
		}

		System.out.println("데이터베이스가 입력되었습니다.");

	}

	// PreparedStatment, excuteUpdate
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

	private boolean connectDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "scott", "tiger");
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

	private void deleteDepttriggerPrepared(int from, int to) {

		try {
			PreparedStatement ps = con.prepareStatement("delete from depttrigger where ? <= id and id <= ?");
			ps.setInt(1, from);
			ps.setInt(2, to);
			ps.executeUpdate();

			System.out.println("데이터가 삭제되었습니다.");

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private void deleteDepttriggerStatement(int from, int to) {

		try {
			Statement stmt = con.createStatement();
			int cnt = stmt
					.executeUpdate(String.format("delete from depttrigger where %d <= id and id <= %d", from, to));

			System.out.println("데이터가 삭제되었습니다.");

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private void updateDeptPrepared(String dno, String dname, int budget) {

		try {
			PreparedStatement ps = con.prepareStatement("update dept set dname = ?, budget = ? where dno = ?");
			ps.setString(1, dname);
			ps.setInt(2, budget);
			ps.setString(3, dno);
			ps.executeUpdate();

			System.out.println("데이터가 교체되었습니다.");

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private void updateDeptStatement(String dno, String dname, int budget) {

		try {
			Statement stmt = con.createStatement();
			int cnt = stmt.executeUpdate(
					String.format("update dept set dname = '%s', budget = %d where dno = '%s'", dname, budget, dno));

			System.out.println("데이터가 교체되었습니다.");

		} catch (SQLException e) {

			e.printStackTrace(); // 어떤 오류가 있는지 알려주는게 얘다. 없으면 오류가 있어도 안뜸 (해결한다고 생각해서)
		}
	}

	public static void main(String[] args) {
		sqlconnection2 tt = new sqlconnection2();
		if (tt.connectDB()) {
//			tt.insertDept("d10", "dname10", 123);
//			tt.insertDeptStatement("d31", "dname31", 234);
//			tt.deleteDepttriggerStatement(1, 3);
//			tt.deleteDepttriggerPrepared(5,7);
//			tt.updateDeptPrepared("d1", "dname1", 1000);
			tt.updateDeptStatement("d3", "dname3", 400);
			tt.closeDB();
		}
	}
}

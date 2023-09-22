package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

class QueryExe {
	int num;
	String text;

	QueryExe(int num, String text) {
		this.num = num;
		this.text = text;
	}

	int getNum() {
		return num;
	}

	String getText() {
		return text;
	}
}

public class Mission {

	private Connection connectDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "scott", "tiger");

			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void closeDB(Connection con) {
		try {
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		ArrayList<QueryExe> list = new ArrayList<>();

		list.add(new QueryExe(1, "London에 있는 프로젝트 이름을 찾아라."));
		list.add(new QueryExe(2, "프로젝트 j1에 참여하는 공급자의 이름을 찾아라."));
		list.add(new QueryExe(3, "공급 수량이 300이상 750이하인 모든 공급의 sno, pno, qty를 찾아라."));
		list.add(new QueryExe(4, "부품의 color와 city의 모든 쌍을 찾아라. 같은 쌍은 한 번만 검색되어야 한다."));
		list.add(new QueryExe(5, "같은 도시에 있는 공급자, 부품, 프로젝트의 sno, pno, jno 쌍을 찾아라. 찾아진 sno, pno, jno의 city들은 모두 같아야 한다."));

		Scanner sc = new Scanner(System.in);
		while (true) {

			for (QueryExe qe : list) {
				System.out.println(String.format("%d. %s", qe.getNum(), qe.getText()));
			}

			System.out.print("선택 <0:exit> : ");
			int sel = sc.nextInt();
			if (sel == 0)
				break;

			switch (sel) {
			case 1:
				exe01();
				break;
			case 2:
				exe02();
				break;
			case 3:
				exe03();
				break;
			case 4:
				exe04();
				break;
			case 5:
				exe05();
				break;
			default:
			}
		}

		System.out.println("시스템을 종료합니다.");
	}

	public static void exe01() {
		Mission tt = new Mission();
		Connection con = tt.connectDB();

		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select jname from j where city = 'London'");

			while (rs.next()) {
				System.out.println(String.format("%s", rs.getString("jname")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("-".repeat(80));

		tt.closeDB(con);
	}

	public static void exe02() {
		Mission tt = new Mission();
		Connection con = tt.connectDB();

		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select sname from s, spj where jno = 'j1'");

			while (rs.next()) {
				System.out.println(String.format("%s", rs.getString("sname")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("-".repeat(80));

		tt.closeDB(con);
	}

	public static void exe03() {
		Mission tt = new Mission();
		Connection con = tt.connectDB();

		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select sno, pno, qty from spj where  300 <=qty and qty<=750");

			while (rs.next()) {
				System.out.println(
						String.format("%s, %s, %d", rs.getString("sno"), rs.getString("pno"), rs.getInt("qty")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("-".repeat(80));

		tt.closeDB(con);
	}

	public static void exe04() {
		Mission tt = new Mission();
		Connection con = tt.connectDB();

		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select distinct color, city from p");

			while (rs.next()) {
				System.out.println(String.format("%s, %s", rs.getString("color"), rs.getString("city")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("-".repeat(80));

		tt.closeDB(con);
	}

	public static void exe05() {
		Mission tt = new Mission();
		Connection con = tt.connectDB();

		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select distinct sno, pno, jno from s, p, j where s.city = p.city and s.city = j.city");

			while (rs.next()) {
				System.out.println(String.format("%s, %s, %s", rs.getString("sno"), rs.getString("pno"), rs.getString("jno")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("-".repeat(80));

		tt.closeDB(con);
	}

}

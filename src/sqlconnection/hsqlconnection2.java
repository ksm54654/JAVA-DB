package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class hsqlconnection2 {

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

	private void insert(int cid, int seq, String num, String type) {

		String sql = "insert into phone (CID, Seq, number, type) values (?, ?, ?, ?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cid);
			ps.setInt(2, seq);
			ps.setString(3, num);
			ps.setString(4, type);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("데이터베이스가 입력되었습니다.");
	}

	public static void main(String[] args) {
		hsqlconnection2 tt = new hsqlconnection2();
		tt.connectDB();
		
		System.out.println("CID : ");
		Scanner sc = new Scanner(System.in);
		int cid = sc.nextInt();
		
		if (cid > 0) {
			System.out.println("Seq : ");
			Scanner sc1 = new Scanner(System.in);
			int seq = sc.nextInt();

			System.out.println("number : ");
			Scanner sc2 = new Scanner(System.in);
			String num = sc.next();

			System.out.println("type('cellphone', 'home telephone', 'company phone', 'fax', 'etc') : ");
			Scanner sc3 = new Scanner(System.in);
			String type = sc.next();

			tt.insert(cid, seq, num, type);
		}
		else if (cid <= 0) {
			tt.closeDB();
			System.out.println("종료");
		}

	}
}

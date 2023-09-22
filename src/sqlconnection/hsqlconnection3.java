package sqlconnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class hsqlconnection3 {
	
	
	public static void main(String[] args) {
		String str = "1999-10-10";
				
		Date dd = Date.valueOf(str);
		System.out.println(dd);
	}
}
 // contact 생일 부분 하는데 참고.
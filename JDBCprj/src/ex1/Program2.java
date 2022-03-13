package ex1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.plaf.multi.MultiInternalFrameUI;
import javax.swing.plaf.synth.SynthOptionPaneUI;

import oracle.net.aso.i;

//데이터 추가하기
public class Program2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		int id= 0000003;
		String name="크로스";
		String posi ="MF";
		int backno = 8;
		String TEAMID="S01";
		
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String sql = "INSERT INTO PLAYER("
				+ "PLAYER_ID,"
				+ "PLAYER_NAME,"
				+ "POSITION,"
				+ "BACK_NO,"
				+ "TEAM_ID"
				+ ")VALUES(?,?,?,?,?)";
		
	
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "YKM", "4608");
//		Statement st = con.createStatement(); //실행도구
		//ResultSet rs = st.executeQuery(sql); //쿼리
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, id);
		st.setString(2, name);
		st.setString(3, posi);
		st.setInt(4, backno);
		st.setString(5, TEAMID);
		
	
		
		int result = st.executeUpdate(); //제대로 되면 1반환 // insert, update,delete

		System.out.println(result);
	
		st.close();
		con.close();	//역순으로 닫기 
		
		
		}
	}
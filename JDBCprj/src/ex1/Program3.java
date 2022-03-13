package ex1;
// 데이터 수정하기
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
import oracle.net.aso.s;

public class Program3 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		
		String name="정경륭";
		String posi ="DF";
		int backno = 5;
		String TEAMID= "K06";
		
		int id= 0000005;
		
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String sql = "UPDATE PLAYER "
				+ "SET"
				+ " PLAYER_NAME = ?,"
				+ " POSITION= ?,"
				+ " BACK_NO =?,"
				+ " TEAM_ID =?"
				+ "WHERE PLAYER_ID =?";
		
	
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "YKM", "4608");
//		Statement st = con.createStatement(); //실행도구
		//ResultSet rs = st.executeQuery(sql); //쿼리
		PreparedStatement st = con.prepareStatement(sql);
		
//		st.setInt(1, playerid);
		
		st.setString(1, name);
		st.setString(2, posi);
		st.setInt(3, backno);
		st.setString(4, TEAMID);
		st.setInt(5, id);
	
		
		int result = st.executeUpdate(); //제대로 되면 1반환 // insert, update,delete

		System.out.println(result);
	
		st.close();
		con.close();	//역순으로 닫기 
		
		
		}
	}
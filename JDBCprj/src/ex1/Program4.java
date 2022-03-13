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

public class Program4 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
	
		
		int id = 0000003;;
		
		
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String sql = "DELETE PLAYER WHERE PLAYER_ID = ?";
		
	
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "YKM", "4608");
//		Statement st = con.createStatement(); //실행도구
		//ResultSet rs = st.executeQuery(sql); //쿼리
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);
		
		int result = st.executeUpdate(); //제대로 되면 1반환 // insert, update,delete

		System.out.println(result);
	
		st.close();
		con.close();	//역순으로 닫기 
		
		
		}
	}
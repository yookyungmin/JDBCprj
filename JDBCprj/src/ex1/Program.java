package ex1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.plaf.multi.MultiInternalFrameUI;
import javax.swing.plaf.synth.SynthOptionPaneUI;

import oracle.net.aso.i;

//연동소스
public class Program {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String sql = "SELECT *FROM PLAYER WHERE TEAM_ID='K05'";
		
		/*
		 * 
		 * String url = "jdbc:oracle:thin@localhost:1521/xe";
		 * 	String sql = "SELECT *FROM PLAYER";
		 * 
		 * class.forName("oracle.jdbc.driver.OracleDriver");
		 * Connection con = DriverManager.getConnection(utl,"SAMPE","4608");
		 * Statement st = con.createStatement(); //실행도구
		 * ResultSet rs = st.executeQuery(sql);
		 */
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "YKM", "4608");
		Statement st = con.createStatement(); //실행도구
		ResultSet rs = st.executeQuery(sql); //쿼리
//		ResultSet rs = st.executeUpdate(sql); //insert, update, delete 조작언어 사용할떄

		
		
	while(rs.next()){ //가져오는것
		int id = rs.getInt("PLAYER_ID");
		String name = rs.getString("PLAYER_NAME");
		String posi = rs.getString("POSITION");
		int backno = rs.getInt("BACK_NO");
		String TEAMID = rs.getString("TEAM_ID");

//		Date regDate = rs.getDate("REGDATE");
	
		
		System.out.printf("id= %d, name= %s, POSITION = %s, backNo=%d, TEAMID=%s\n",id, name,posi, backno,TEAMID);
		System.out.println();
	

//		System.out.printf("id :%d, title:%s, writerid:%s, regDate:%s, content:%s, hit:%d\n", 
//				id, title, writerId, regDate, content, hit);
}

		rs.close();
		st.close();
		con.close();	//역순으로 닫기 
		
		
		}
	}

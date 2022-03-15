package com.ykm.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ykm.app.entity.Player;

import oracle.net.aso.p;
import oracle.net.aso.s;



public class PlayerService {
	private String url = "jdbc:oracle:thin:@localhost:1521/xe";
	private String uid = "ykm";
	private String pwd = "4608";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	
	
 	public List<Player> getList(int page) throws ClassNotFoundException, SQLException{
		int start =1+(page-1)*10; //1, 11, 21, 31
		int end = 5*page; // 5 10 15
//		String sql = "SELECT *FROM ("
//				+ "SELECT ROWNUM NUM, P.*FROM("
//				+ "SELECT *FROM PLAYER WHERE TEAM_ID = 'K06')P"
//				+ ")"
//				+ "WHERE NUM BETWEEN ? AND ?";
		String sql = "SELECT *FROM PLAYER_VIEW WHERE NUM BETWEEN ? AND ?";
		

		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		PreparedStatement st = con.prepareStatement(sql); //? 사용시
		
//		Statement st = con.createStatement(); //실행도구
		
		st.setInt(1, start);
		st.setInt(2, end);
		ResultSet rs = st.executeQuery(); //쿼리 //메서드마다 만들어야됨 4줄은
//		ResultSet rs = st.executeUpdate(sql); //insert, update, delete 조작언어 사용할떄

		List<Player> list = new ArrayList<Player>(); 
		
	while(rs.next()){ //가져오는것
		int id = rs.getInt("PLAYER_ID");
		String name = rs.getString("PLAYER_NAME");
		String posi = rs.getString("POSITION");
		int backno = rs.getInt("BACK_NO");
		String TEAMID = rs.getString("TEAM_ID");
//		Date regDate = rs.getDate("REGDATE");
	
			Player player = new Player(
					id,
					name,
					posi,
					backno,
					TEAMID
					);
			
			list.add(player);
	}



		rs.close();
		st.close();
		con.close();	//역순으로 닫기 
		
	
		return list;
	}

	public int insert(Player player) throws SQLException, ClassNotFoundException {
		

		int id= player.getId();
		String name= player.getName();
		String posi =player.getPosi();
		int backno = player.getBackno();
		String TEAMID=player.getTEAMID();
	
		
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String sql = "INSERT INTO PLAYER("
				+ "PLAYER_ID,"
				+ "PLAYER_NAME,"
				+ "POSITION,"
				+ "BACK_NO,"
				+ "TEAM_ID"
				+ ")VALUES(?,?,?,?,?)";
		
	
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
//		Statement st = con.createStatement(); //실행도구
		//ResultSet rs = st.executeQuery(sql); //쿼리
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, id);
		st.setString(2, name);
		st.setString(3, posi);
		st.setInt(4, backno);
		st.setString(5, TEAMID);

		
	
		
		int result = st.executeUpdate(); //제대로 되면 1반환 // insert, update,delete


	
		st.close();
		con.close();	//역순으로 닫기 
		return result;
		
	}
	public int update(Player player) throws SQLException, ClassNotFoundException {
		
		
		String name= player.getName();
		String posi =player.getPosi();
		int backno = player.getBackno();
		String TEAMID= player.getTEAMID();
		
		int id= player.getId();
		
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String sql = "UPDATE PLAYER "
				+ "SET"
				+ " PLAYER_NAME = ?,"
				+ " POSITION= ?,"
				+ " BACK_NO =?,"
				+ " TEAM_ID =?"
				+ "WHERE PLAYER_ID =?";
		
	
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
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

		
	
		st.close();
		con.close();	//역순으로 닫기 
		return result;
	}
	
	public int delete(int id) throws ClassNotFoundException, SQLException {
		
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String sql = "DELETE PLAYER WHERE PLAYER_ID = ?";
		
	
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
//		Statement st = con.createStatement(); //실행도구
		//ResultSet rs = st.executeQuery(sql); //쿼리
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);
		
		int result = st.executeUpdate(); //제대로 되면 1반환 // insert, update,delete

	
	
		st.close();
		con.close();	//역순으로 닫기 
		return result;
	}
}

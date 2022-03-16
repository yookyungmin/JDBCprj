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
	
	
	
 	public List<Player> getList(int page, String field, String query) throws ClassNotFoundException, SQLException{
		int start =1+(page-1)*5; //
		int end = 5*page; // page 2일떄 6 ~ 10
		
//		String sql = "SELECT *FROM ("
//				+ "SELECT ROWNUM NUM, P.*FROM("
//				+ "SELECT *FROM PLAYER WHERE TEAM_ID = 'K06')P"
//				+ ")"
//				+ "WHERE NUM BETWEEN ? AND ?";
		
//		String sql = "SELECT *FROM PLAYER_VIEW WHERE NUM BETWEEN ? AND ?";
//		String sql = "SELECT *FROM ("
//				+ "SELECT ROWNUM NUM, PLAYER.*FROM PLAYER WHERE TEAM_ID = 'K06'"
//				+ ")"
//				+ "WHERE NUM BETWEEN ? AND ?";  // TEAMID가 K06인선수들출력
		
		String sql = ""
				+ "SELECT *FROM ("
				+ "SELECT ROWNUM NUM, P.*FROM ("
				+ "SELECT *FROM PLAYER ORDER BY PLAYER_ID DESC"
				+ ")P"
				+ ")"
				+ "WHERE "+field+" LIKE ? AND NUM BETWEEN ? AND ?";  //PLAYER ID 내림차순으로
		


		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		
		PreparedStatement st = con.prepareStatement(sql); //? 사용시
		st.setString(1, "%"+query+"%");
		st.setInt(2, start);
		st.setInt(3, end);
		
//		Statement st = con.createStatement(); //실행도구
		
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
 	
	//Scalar값을 얻어온다(단일값)
 	
	public int getCount() throws ClassNotFoundException, SQLException {
		//갯수확인
		int count = 0;
		String sql = "SELECT COUNT (PLAYER_ID) COUNT FROM PLAYER";
				


		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);


		
		Statement st = con.createStatement(); //실행도구
		
		//prepared아닐시엔 executequery에 sql 붙여줌
		ResultSet rs = st.executeQuery(sql); //쿼리 //메서드마다 만들어야됨 4줄은


		List<Player> list = new ArrayList<Player>(); 
		
		if(rs.next())
		count = rs.getInt("COUNT");
		
		rs.close();
		st.close();
		con.close();	//역순으로 닫기 
		
		return count;
	}

	public int insert(Player player) throws SQLException, ClassNotFoundException {
		//데이터추가

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
		//데이터 수정
	
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
		//데이터 삭제
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

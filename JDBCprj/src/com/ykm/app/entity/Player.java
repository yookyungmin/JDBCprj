package com.ykm.app.entity;

public class Player {
	
	private int id;
	private String name;
	private String posi;
	private int backno;
	private String TEAMID;
	
	public Player(){
		
	}
	
	
	
	public Player(int id, String name, String posi, int backno, String TEAMID) {
		
		this.id = id;
		this.name = name;
		this.posi = posi;
		this.backno = backno;
		this.TEAMID = TEAMID;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosi() {
		return posi;
	}
	public void setPosi(String posi) {
		this.posi = posi;
	}
	public int getBackno() {
		return backno;
	}
	public void setBackno(int backno) {
		this.backno = backno;
	}
	public String getTEAMID() {
		return TEAMID;
	}
	public void setTEAMID(String tEAMID) {
		TEAMID = tEAMID;
	}
		 
}

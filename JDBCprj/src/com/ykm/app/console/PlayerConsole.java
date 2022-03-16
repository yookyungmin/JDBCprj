package com.ykm.app.console;

import java.security.Provider.Service;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.w3c.dom.css.Counter;

import com.ykm.app.entity.Player;
import com.ykm.app.service.PlayerService;

import oracle.net.aso.n;

public class PlayerConsole {
	
	private PlayerService service;
	private int page; //현재 페이지
	private String searchField;
	private String searchWord;

	
	public PlayerConsole() {
		service = new PlayerService();
		page = 1; //페이지 기본값
		searchField = "PLAYER_ID";
		searchWord = "";


	}
	public void printPlayerList() throws ClassNotFoundException, SQLException {
		List<Player> list = service.getList(page,searchField, searchWord); //list로 얻어서 service가 제공하는 목록을
		
		int count = service.getCount(); //현재 데이터베이스의 테이블 데이터 갯수
		int lastpage = count/5; //
		lastpage = count%5>0?lastpage+1:lastpage; //나머지가 0보다 크다면 +1 , 아니면 그대로
		System.out.println("==========================================================");
		System.out.printf("<K06선수목록> 총 %d 선수명\n",count);
		System.out.println("==========================================================");
	
		for(Player n:list) {
			
			System.out.printf("%d. %s / %s / "
					+ "%d /%s\n",
					n.getId(),
					n.getName(),
					n.getPosi(),
					n.getBackno(),
					n.getTEAMID()
					);
		}
		System.out.println("==========================================================");
		System.out.printf("                     %d/%d pages\n",page,lastpage);
	}

	public int inputPlayerMenu() {
		
		Scanner sc = new Scanner(System.in);
		
		
			System.out.print("1.상세조회/ 2.이전/ 3.다음/ 4.글쓰기 / 5.검색 / 6.종료> ");
			int menu = Integer.parseInt(sc.nextLine());
			
		return menu;
	}
	public void movePrevList() {
		if(page==1) {
			System.out.println("============================");
			System.out.println("이전 페이지가 없습니다");
			System.out.println("============================");
			return;
		}
		page--;
		
	}
	public void moveNextList() throws ClassNotFoundException, SQLException {
		int count = service.getCount(); //현재 데이터베이스의 테이블 데이터 갯수
		int lastpage = count/5; //
		lastpage = count%5>0?lastpage+1:lastpage; //나머지가 0보다 크다면 +1 , 아니면 그대로
		
		if(page==lastpage) {
			System.out.println("============================");
			System.out.println("다음 페이지가 없습니다");
			System.out.println("============================");
			return;
		}
		page++;
		}
	public void inputSearchWord() {
		Scanner sc = new Scanner(System.in);
		System.out.println("검색 범주(PLAYER_ID/PLAYER_NAME)중에 하나를 입력하세요");
		System.out.print(" > ");
		searchField = sc.nextLine();
		
		System.out.print("검색어 > ");
		searchWord = sc.nextLine();
	}

}

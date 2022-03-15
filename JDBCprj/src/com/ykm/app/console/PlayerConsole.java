package com.ykm.app.console;

import java.security.Provider.Service;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ykm.app.entity.Player;
import com.ykm.app.service.PlayerService;

import oracle.net.aso.n;

public class PlayerConsole {
	
	private PlayerService service;
	
	public PlayerConsole() {
		service = new PlayerService();
	}
	public void printPlayerList() throws ClassNotFoundException, SQLException {
		List<Player> list = service.getList(1); //list로 얻어서 service가 제공하는 목록을
		
		System.out.println("==========================================================");
		System.out.printf("<K06선수목록> 총 %d 선수명\n",11);
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
		System.out.printf("                     %d/%d pages\n",1,2);
	}

	public int inputPlayerMenu() {
		
		Scanner sc = new Scanner(System.in);
		
		
			System.out.print("1.상세조회/ 2.이전/ 3.다음/ 4.글쓰기/ 5.종료> ");
			int menu = Integer.parseInt(sc.nextLine());
			
		return menu;
	}

}

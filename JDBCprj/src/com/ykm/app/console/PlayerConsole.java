package com.ykm.app.console;

import java.security.Provider.Service;
import java.sql.SQLException;
import java.util.List;

import com.ykm.app.entity.Player;
import com.ykm.app.service.PlayerService;

public class PlayerConsole {
	
	private PlayerService service;
	
	public PlayerConsole() {
		service = new PlayerService();
	}
	public void printPlayerList() throws ClassNotFoundException, SQLException {
		List<Player> list = service.getList(); //list로 얻어서 service가 제공하는 목록을
		
		System.out.println("==========================================================");
		System.out.printf("<베스트 일레븐> 총 %d 게시글\n",12);
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
		// TODO Auto-generated method stub
		return 0;
	}

}

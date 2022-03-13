package ex1;

import java.sql.SQLException;

import com.ykm.app.console.PlayerConsole;

public class Program5 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		PlayerConsole console = new PlayerConsole();
		console.printPlayerList();
		int menu = console.inputPlayerMenu();

		switch (menu) {
		case 1: // 상세조회
			break;
		case 2://이전
			break;
		case 3: //다음
			break;
		case 4: //글쓰기
			break;
		}
	}

}

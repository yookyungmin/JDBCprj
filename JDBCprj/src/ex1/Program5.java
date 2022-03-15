package ex1;

import java.sql.SQLException;

import com.ykm.app.console.PlayerConsole;

public class Program5 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		PlayerConsole console = new PlayerConsole();
		
		exit :
			while(true) {
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
		case 5 ://종료
			System.out.println("bye~");
			break exit;
			default:
				System.out.println("<<사용방법>> 메뉴는 1~4번까지만 입력할수 있씁니다");
				break;
		}
		}
	}

}

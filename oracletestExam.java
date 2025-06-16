import java.sql.SQLException;
import java.util.Scanner;

import dao.MemberDAO;
import dto.MemberDTO;
import service.MemberService;

public class oracletestExam {
	public static Scanner inputStr = new Scanner(System.in);
	public static Scanner inputInt = new Scanner(System.in);
	public static void main(String[] args) throws SQLException {
		
		boolean run = true;

		while (run) {
			System.out.println("------------자유게시판 서비스------------");
			System.out.println("| 1. 게시판메뉴 | 2. 회원메뉴 | 3. 프로그램종료 |");
			System.out.println("-------------------------------------");
			System.out.print(" 입력 : ");
			String num = inputStr.next();

			switch (num) {
			case "1":
				System.out.println("게시판 메뉴로 이동합니다.");
				break;

			case "2":
				System.out.println("회원 메뉴로 이동합니다.");
				MemberService memberService = new MemberService();
				memberService.memberMenu(inputStr);
				break;

			case "3":
				System.out.println("프로그램을 종료합니다.");
				break;

			case "990514":
				System.out.println("관리자 메뉴로 이동합니다.");
				run = false;
				break;

			default:
				System.out.println("다시 입력해주세요.");
			}

		}
	}

}	

package service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

import dao.MemberDAO;
import dto.MemberDTO;

public class MemberService {

	public void memberMenu(Scanner inputStr) throws SQLException {
		// 회원메뉴
		MemberDTO memberDTO = new MemberDTO();
		MemberDAO memberDAO = new MemberDAO();
		boolean run = true;

		while (run) {
			System.out.println("------------------회원메뉴------------------");
			if (Session.isLoggedIn()) {
				System.out.println("현재 로그인 : [" + Session.getCurrentUser().getmName() + "] 님");

			} else {
				System.out.println("[ 비회원상태 ]");
			}
			System.out.println(" ");
			System.out.println("| 1. 로그인 | 2. 로그아웃 | 3. 회원가입 |");
			System.out.println("| 4. 회원정보 | 5. 회원수정 | 6. 회원탈퇴 | 7. 이전으로 |");
			System.out.println("------------------------------------------");
			System.out.print(" 입력 : ");
			String num = inputStr.next();

			switch (num) {
			case "1":
				System.out.println("-----로그인 메뉴-----.");
				loginMember(memberDAO, inputStr);
				break;

			case "2":
				System.out.println("-----로그아웃 메뉴-----");
				logout(inputStr);
				break;

			case "3":
				System.out.println("-----회원가입 메뉴-----");
				createMember(inputStr, memberDAO);
				break;

			case "4":
				readMember(memberDAO);
				break;

			case "5":
				System.out.println("-----회원수정 메뉴-----");
				modifyMember(inputStr, memberDAO);
				break;

			case "6":
				System.out.println("-----회원탈퇴 메뉴-----");
				deleteMember(inputStr, memberDAO);
				break;

			case "7":
				System.out.println("-----이전으로-----");
				break;

			default:
				System.out.println("다시 입력해주세요.");

			}
		}
	}

	private void deleteMember(Scanner inputStr, MemberDAO memberDAO) {
		MemberDTO memberDTO = Session.getCurrentUser(); // 로그인한 정보

		if (memberDTO == null) {
			System.out.println("세션 정보가 없습니다. 로그인해주세요.");
			return;

		} else {
			System.out.println(memberDTO.getmName() + "님");
			System.out.println("회원을 탈퇴하시겠습니까?");
			System.out.println("| 1. 예 | 2. 아니요 |");
			System.out.print("입력 : ");
			String num = inputStr.next();

			switch (num) {
			case "1":

				boolean result = memberDAO.deleteMemberById(memberDTO.getmId());

				if (result) {
					System.out.println("[회원을 탈퇴하였습니다]");
					System.out.println("------------------------------------------------------");
					Session.logout(); // 세션 초기화
					break;
				} else {
					System.out.println("회원 탈퇴 실패. 관리자에게 문의하세요.");
					break;
				}
			case "2":
				System.out.println("이전으로 돌아갑니다.");
				break;

			default:
				System.out.println("잘못된 입력입니다.");
			}
		}
	}

	private void modifyMember(Scanner inputStr, MemberDAO memberDAO) {
		// 회원수정
		MemberDTO memberDTO = Session.getCurrentUser(); // 로그인정보

		if (memberDTO == null) {
			System.out.println("세션 정보가 없습니다. 로그인해주세요.");
			return;
		}
		System.out.println("-----수정할 회원 정보를 입력하세요-----");

		inputStr.nextLine(); // 버퍼제거용
		System.out.println("(변경하지 않으려면 Eenter)");
		System.out.println(" ");
		System.out.println("수정할 이름 : ");
		String newName = inputStr.nextLine();

		System.out.print("수정할 ID : ");
		String newmId = inputStr.nextLine();

		System.out.print("수정할 PW : ");
		String newmPw = inputStr.nextLine();

		if (newName == null || newName.trim().isEmpty()) {// 입력이 비어있으면~ 기본정보 유지
			newName = memberDTO.getmName();
		}
		if (newmId == null || newmId.trim().isEmpty()) {
			newmId = memberDTO.getmId();
		}
		if (newmPw == null || newmPw.trim().isEmpty()) {
			newmPw = memberDTO.getmPw();
		}

		boolean result = memberDAO.modifyMember(memberDTO.getmId(), newName, newmId, newmPw, newmPw);

		if (result) {
			System.out.println("회원정보 수정이 완료되었습니다.");

			memberDTO.setmName(newName); // 새정보
			memberDTO.setmId(newmId);
			memberDTO.setmPw(newmPw);

		} else {
			System.out.println("회원정보 수정 실패. 다시 확인해주세요.");
		}
	}

	private void readMember(MemberDAO memberDAO) {
		// 회원정보
		MemberDTO memberDTO = Session.getCurrentUser(); // 로그인한 정보

		if (memberDTO == null) {
			System.out.println("세션 정보가 없습니다. 로그인해주세요.");
			return;

		} else {

			System.out.println("-----------회원정보-----------");
			System.out.println("이름 : " + memberDTO.getmName());
			System.out.println("ID : " + memberDTO.getmId());
			System.out.println("PW : " + memberDTO.getmPw());
			System.out.println("회원 등록일 : " + memberDTO.getRegidate());
			System.out.println("----------------------------");
		}
	}

	private void logout(Scanner inputStr) {
		// 로그아웃
		MemberDTO memberDTO = Session.getCurrentUser(); // 로그인한 정보

		if (memberDTO == null) {
			System.out.println("세션 정보가 없습니다. 로그인해주세요.");
			return;

		} else {

			Session session = new Session();
			System.out.println("로그아웃 하시겠습니까?");
			System.out.println("| 1. 예 | 2. 아니요 |");
			System.out.print("입력 : ");
			String num2 = inputStr.next();
			switch (num2) {
			case "1":
				System.out.println("로그아웃 성공! 안녕히가십시오.");
				session.logout();
				break;

			case "2":
				System.out.println("로그인 상태를 유지합니다.");
				break;
			}
		}
	}

	private void loginMember(MemberDAO memberDAO, Scanner inputStr) {
		// 로그인
		MemberDTO memberDTO = new MemberDTO();
		System.out.println("로그인을 진행합니다.");
		System.out.print("ID : ");
		memberDTO.setmId(inputStr.next());

		System.out.print("PW : ");
		memberDTO.setmPw(inputStr.next());

		memberDAO.login(memberDTO);
	}

	private void createMember(Scanner inputStr, MemberDAO memberDAO) throws SQLException {
		MemberDTO memberDTO = new MemberDTO();
		System.out.println("회원가입을 진행합니다.");
		System.out.print("이름 : ");
		memberDTO.setmName(inputStr.next());

		System.out.print("사용할ID : ");
		memberDTO.setmId(inputStr.next());

		System.out.print("사용할PW : ");
		memberDTO.setmPw(inputStr.next());

		if (memberDAO.duplicateId(memberDTO, memberDTO.getmId())) {
			System.out.println("이미 존재하는 ID입니다. 다른 ID를 입력해주세요.");
			return;
		}
		memberDAO.insertMember(memberDTO);

	}

}

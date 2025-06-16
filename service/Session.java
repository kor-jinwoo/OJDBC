package service;
import dto.MemberDTO;

	public class Session { // 로그인 상태 관리

		private static MemberDTO currentUser; // 로그인한 사용자 정보를 저장하는 변수

		public static void loginM(MemberDTO user) { // 로그인할때 호출
			currentUser = user; // 로그인 성공 후 memberDTO 객체를 넘겨받아 currentUser에 저장
		}

		public static void logout() { // 로그아웃 메서드
			currentUser = null; // 로그인 정보를 초기화
		}

		public static boolean isLoggedIn() { // 로그인 상태 확인 메서드
			return currentUser != null; // currentUser가 null이 아니면 로그인한 상태 -> true로 반환

		}

		public static MemberDTO getCurrentUser() { // 로그인한 사용자 정보 가져오는 메서드
			return currentUser; // 현재 로그인한 사용자희 memberDTO객체를 반환
			// 로그인하지 않았다면 NULL을 반환
		}
	}

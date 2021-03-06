package co.animalMate.main.command;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.animalMate.board.dao.MypageDao;
import co.animalMate.board.dao.PetDao;
import co.animalMate.common.Action;
import co.animalMate.login.dao.BookmarkDAO;
import co.animalMate.login.dao.MemberDao;
import co.animalMate.vo.BookMarkVO;
import co.animalMate.vo.MemberVO;
import co.animalMate.vo.PetVO;
import co.animalMate.vo.TradeBoardVO;

public class GoMypagePageAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 마이페이지 DB 정보 출력
		PetVO petVo = new PetVO();
		TradeBoardVO tbVo = new TradeBoardVO();
		MemberVO memVo = new MemberVO();
		
		
		List<PetVO> list = new ArrayList<PetVO>();

		MypageDao myDao = new MypageDao();
		PetDao petDao = new PetDao();

		// 세션 아이디 정보
		HttpSession session = request.getSession(false);
		String id = (String) session.getAttribute("id");
		petVo.setId(id);

		// User 펫 리스트 출력
		petVo.setId(id);
		list = petDao.selectPets(petVo);
		
		// User 정보 출력
		memVo.setId(id);
		memVo = myDao.userInfo(memVo);
		
		// 거래 횟수 호출
		myDao = new MypageDao();
		tbVo.setBuyer(id);
		tbVo.setSeller(id);
		tbVo = myDao.tradeCount(tbVo);
	
		// 나이 구하기!
		int birthYear = Integer.parseInt(String.valueOf(memVo.getZoomin1()).substring(0, 2));
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR) - 2000;
		int age;
		if (birthYear > year) {
			age = year + (100 - birthYear) + 1; // 1더하면 한국나이
		} else {
			age = year;
		}
		
		request.setAttribute("age", age);
				

		request.setAttribute("tcount", tbVo);
		request.setAttribute("pets", list);
		request.setAttribute("user", memVo);
		
		BookmarkDAO markdao = new BookmarkDAO();
		BookMarkVO markvo = new BookMarkVO();
		
		List<BookMarkVO> marklist = new ArrayList<BookMarkVO>();
		markvo.setId(id);
		marklist = markdao.selectAllById(markvo);
		
		MemberVO memberVo = new MemberVO();
		MemberDao memberDao = new MemberDao();
		List<MemberVO> memberlist = new ArrayList<MemberVO>();
		
		for(BookMarkVO a : marklist) {
			String b = a.getMarkId();
			memberVo = new MemberVO();
			memberDao = new MemberDao();
			memberVo.setId(b);
			memberVo = memberDao.selectById(memberVo);
			
			
			//나이구하기
			birthYear = Integer.parseInt(String.valueOf(memberVo.getZoomin1()).substring(0, 2));
			cal = Calendar.getInstance();
			year = cal.get(Calendar.YEAR) - 2000;
			if (birthYear > year) {
				age = year + (100 - birthYear) + 1; // 1더하면 한국나이
			} else {
				age = year;
			}
			memberVo.setZoomin1(age);
			memberlist.add(memberVo);
		}
		request.setAttribute("memberlist", memberlist);
		
		return "jsp/mypage/mypage.jsp";
	}

}

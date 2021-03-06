package co.animalMate.mypage.command;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.animalMate.board.dao.MypageDao;
import co.animalMate.common.Action;
import co.animalMate.main.dao.CommentsDAO;
import co.animalMate.main.dao.TradeBoardDAO;
import co.animalMate.vo.CommentsVO;
import co.animalMate.vo.JoblistVO;
import co.animalMate.vo.MemberVO;
import co.animalMate.vo.TradeBoardVO;
import co.animalMate.vo.TradeListVO;

public class OwnerFormCheck implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 돌봐주세요 화면 호출
		MypageDao myDao = new MypageDao();
		TradeListVO tlVo = new TradeListVO();
		MemberVO memVo = new MemberVO();

		// 세션 아이디 정보
		HttpSession session = request.getSession(false);
		String sid = (String) session.getAttribute("id");
		request.setAttribute("sid", sid);

		// 펫, 거래 게시판 정보 호출
		int code = Integer.parseInt(request.getParameter("code"));
		tlVo.setCode(code);// 거래번호 클릭시
		tlVo = myDao.selectUserTrade(tlVo);
		request.setAttribute("tbs", tlVo);
		
		//체크리스트 출력
		myDao = new MypageDao();
		JoblistVO jobVo = new JoblistVO();
		List<JoblistVO> joblist = new ArrayList<JoblistVO>();
		jobVo.setCode(code);
		joblist = myDao.selectJoblist(jobVo);
		request.setAttribute("jobs", joblist);
		
		// 상대 시터정보 호출
		myDao = new MypageDao();
		if(sid.equals(tlVo.getSeller())) {
			memVo.setId(tlVo.getBuyer());
		}else{			
			memVo.setId(tlVo.getSeller());
		}
		memVo = myDao.userInfo(memVo);
		request.setAttribute("user", memVo);

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

		// 성별 구하기!
		String zoo2 = String.valueOf(memVo.getZoomin2()).substring(0, 1);
		String gender;
		if (zoo2.equals("1") || zoo2.equals("3")) {
			gender = "남성";
		} else {
			gender = "여성";
		}
		request.setAttribute("gender", gender);
		
		// 거래완료 횟수
		TradeBoardDAO tradeBoardDAO = new TradeBoardDAO();
		TradeBoardVO tradeBoardVO = new TradeBoardVO();
		List<TradeBoardVO> tradeBoardList = new ArrayList<TradeBoardVO>();
		tradeBoardVO.setSeller(sid);//바꿔줘야함
		tradeBoardList = tradeBoardDAO.selectById(tradeBoardVO);
		int career = tradeBoardList.size(); 
		request.setAttribute("career", career);
		
		// 경력
		CommentsVO commentsVO = new CommentsVO();
		CommentsDAO commentsDAO = new CommentsDAO();
		int score = 0;
		for (TradeBoardVO tempt : tradeBoardList) {
			commentsVO.setCode(tempt.getCode());
			commentsVO = commentsDAO.selectByCode(commentsVO);
			score += commentsVO.getScore();
		}
		if (career != 0) {
			request.setAttribute("score", score / career);
		} else {
			request.setAttribute("score", "거래내역 없음");
		}
		
		return "jsp/mypage/ownerFormCheck.jsp";
	}

}

package co.animalMate.mypage.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.animalMate.board.dao.MypageDao;
import co.animalMate.common.Action;
import co.animalMate.vo.MemberTradeListVO;
import co.animalMate.vo.SitterTradeCheckListVO;
import co.animalMate.vo.TradeBoardVO;
import co.animalMate.vo.TradeListVO;

public class SitterTradeCheck implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 돌봐주세요 화면 호출
		MypageDao myDao = new MypageDao();
		TradeListVO tlVo = new TradeListVO();
		SitterTradeCheckListVO stclVo = new SitterTradeCheckListVO();

		// 세션 아이디 정보
		HttpSession session = request.getSession(false);
		String sid = (String) session.getAttribute("id");
		request.setAttribute("sid", sid);

		// 거래 게시판 정보 호출
		TradeBoardVO tbVo = new TradeBoardVO();
		String codeString = request.getParameter("code");
		int code = Integer.parseInt(request.getParameter("code"));// 클릭한 거래게시판 번호
		tbVo.setCode(code);// 거래번호 클릭시
		tbVo = myDao.selectUserTrade2(tbVo);
		request.setAttribute("tbs", tbVo);

		// 거래 요청리스트 호출
		MemberTradeListVO memtlVo = new MemberTradeListVO();
		List<MemberTradeListVO> memlist = new ArrayList<MemberTradeListVO>();
		myDao = new MypageDao();
		memtlVo.setId(codeString);
		memlist = myDao.selectApplyTradeList(memtlVo);
		request.setAttribute("subUser", memlist);

		// 유저 돈 체크
		myDao = new MypageDao();
		String buyer = tlVo.getBuyer();
		tlVo = new TradeListVO();
		tlVo.setBuyer(buyer);
		tlVo = myDao.selectUserPoint(tlVo);
		request.setAttribute("userP", tlVo.getPrice());

		return "jsp/mypage/sitterTradeCheck.jsp";
	}

}

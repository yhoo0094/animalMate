package co.animalMate.main.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.animalMate.common.Action;

public class GotradeListPageAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		return "jsp/mypage/mytradeList.jsp";
	}

}

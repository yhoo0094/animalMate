package co.animalMate.main.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.animalMate.common.Action;

public class GoSitterListPageAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		
		return "/jsp/board/sitterList.jsp";
	}

}

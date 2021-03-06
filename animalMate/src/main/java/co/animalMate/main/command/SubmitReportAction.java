package co.animalMate.main.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.animalMate.common.Action;
import co.animalMate.main.dao.BlackDAO;
import co.animalMate.vo.BlackVO;

public class SubmitReportAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String title = request.getParameter("title");
		String toUser = request.getParameter("toUser");
		String comm = request.getParameter("comm");
		
		BlackVO vo = new BlackVO();
		vo.setFromUser((String) session.getAttribute("id"));
		vo.setTitle(title);
		vo.setToUser(toUser);
		vo.setComm(comm);
		
		BlackDAO dao = new BlackDAO();
		dao.insert(vo);
		
		try {
			response.sendRedirect(request.getContextPath() + "/goBlackBoardPage.do");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}

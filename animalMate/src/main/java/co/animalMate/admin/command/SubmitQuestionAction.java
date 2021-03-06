package co.animalMate.admin.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import co.animalMate.common.Action;
import co.animalMate.vo.QuestionVO;

public class SubmitQuestionAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		   
		
		
		String id=request.getParameter("id");
		String title=request.getParameter("title");
		String status=request.getParameter("status");
		String ttype=request.getParameter("ttype");
		String comm=request.getParameter("comm");
		
		QuestionVO vo=new QuestionVO();
		QuestionDAO dao=new QuestionDAO();
		vo.setSend(id);
		vo.setTitle(title);
		vo.setStatus(status);
		vo.setTtype(ttype);
		vo.setComm(comm);
		dao.insert(vo);
		try {
			response.sendRedirect(request.getContextPath()+"/questionlist.do");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}

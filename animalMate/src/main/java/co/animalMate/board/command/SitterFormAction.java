package co.animalMate.board.command;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.animalMate.common.Action;
import co.animalMate.main.dao.CommentsDAO;
import co.animalMate.main.dao.MemberDao;
import co.animalMate.main.dao.PictureDAO;
import co.animalMate.main.dao.SitterDAO;
import co.animalMate.main.dao.TradeBoardDAO;
import co.animalMate.vo.CommentsVO;
import co.animalMate.vo.MemberVO;
import co.animalMate.vo.PictureVO;
import co.animalMate.vo.SitterVO;
import co.animalMate.vo.TradeBoardVO;

public class SitterFormAction implements Action {

   @Override
   public String exec(HttpServletRequest request, HttpServletResponse response) {
      HttpSession session = request.getSession();
      String id = (String) session.getAttribute("id");
      
      //멤버테이블 정보 보내기
      MemberDao dao = new MemberDao();
      MemberVO vo = new MemberVO();
      vo.setId(id);
      vo = dao.selectById(vo);
      request.setAttribute("members", vo);
      
      //시터테이블 정보 보내기
      SitterDAO sitterDAO = new SitterDAO();
      SitterVO sitterVO = new SitterVO();
      sitterVO.setId(id);
      sitterVO = sitterDAO.selectById(sitterVO);
      request.setAttribute("sitter", sitterVO);
      
      //나이 구하기!
      int birthYear = Integer.parseInt(String.valueOf(vo.getZoomin1()).substring(0, 2));
      Calendar cal = Calendar.getInstance();
      int year = cal.get(Calendar.YEAR)-2000;
      int age;
      if(birthYear > year) {
         age = year + (100-birthYear) +1; //1더하면 한국나이
      } else {
         age = year;
      }
      request.setAttribute("age", age);
      
      //성별 구하기!
      String zoo2 = String.valueOf(vo.getZoomin2()).substring(0, 1);
      String gender;
      if(zoo2.equals("1") || zoo2.equals("3")) {
         gender = "남성";
      } else {
         gender = "여성";
      }
      request.setAttribute("gender", gender);
      
      //평점이랑 거래횟수 구하기!
      TradeBoardDAO tradeBoardDAO = new TradeBoardDAO();
      TradeBoardVO tradeBoardVO = new TradeBoardVO();
      List<TradeBoardVO> tradeBoardList = new ArrayList<TradeBoardVO>();
      tradeBoardVO.setSeller(id);
      tradeBoardList = tradeBoardDAO.selectById(tradeBoardVO);
      int career = tradeBoardList.size(); //거래완료 횟수
      request.setAttribute("career", career);
      
      
      int score = 0;
      for(TradeBoardVO tempt : tradeBoardList) {
    	  CommentsVO commentsVO = new CommentsVO();
          CommentsDAO commentsDAO = new CommentsDAO();
         commentsVO.setCode(tempt.getCode());
         commentsVO = commentsDAO.selectByCode(commentsVO);
         score += commentsVO.getScore();
      }
      if(career!=0) {
    	  request.setAttribute("score", score/career);
      } else {
    	  request.setAttribute("score", "거래내역 없음");
      }
      
      //돌봄환경 사진 구하기!
      PictureDAO pictureDAO = new PictureDAO();  
      PictureVO pictureVO = new PictureVO();  
      pictureVO.setId(id);
      List<PictureVO> pictureList = new ArrayList<PictureVO>();
      pictureList = pictureDAO.selectById(pictureVO);
      request.setAttribute("pictureList", pictureList);
      
      //수정하기 일 때 code기반 tradeboard정보 뿌리기
      if(request.getParameter("code") != null) {
    	  int code = Integer.parseInt(request.getParameter("code"));
    	  tradeBoardDAO = new TradeBoardDAO();
          tradeBoardVO = new TradeBoardVO();
          tradeBoardVO.setCode(code);
          tradeBoardVO = tradeBoardDAO.selectByCode(tradeBoardVO);
          request.setAttribute("tradeBoardVO", tradeBoardVO);
      }
      
      return  "jsp/board/sitterForm.jsp";
   }

}
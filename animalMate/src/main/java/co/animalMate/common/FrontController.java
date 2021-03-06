package co.animalMate.common;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.animalMate.admin.command.BlackListAction;
import co.animalMate.admin.command.BlackSearchAction;
import co.animalMate.admin.command.BlackUpdateAction;
import co.animalMate.admin.command.GoQuestionBoardPageAction;
import co.animalMate.admin.command.MemberListAction;
import co.animalMate.admin.command.MemberSearchAction;
import co.animalMate.admin.command.MemberUpdateAction;
import co.animalMate.admin.command.QuestionAnswerAction;
import co.animalMate.admin.command.QuestionRewriteAction;
import co.animalMate.admin.command.QuestionlistAction;
import co.animalMate.admin.command.QuestionselectAction;
import co.animalMate.admin.command.Result;
import co.animalMate.admin.command.ReviewInsertAction;
import co.animalMate.admin.command.ReviewlistAction;
import co.animalMate.admin.command.SitterInsertlistAction;
import co.animalMate.admin.command.SitterSearchAction;
import co.animalMate.admin.command.SitterlistAction;
import co.animalMate.admin.command.SubmitQuestionAction;
import co.animalMate.admin.command.tradeAction;
import co.animalMate.board.command.MainAction;
import co.animalMate.board.command.OwnerFormAction;
import co.animalMate.board.command.OwnerFormViewAction;
import co.animalMate.board.command.OwnerInsertAction;
import co.animalMate.board.command.OwnerListAction;
import co.animalMate.board.command.OwnerListSearhAction;
import co.animalMate.board.command.SitterFormAction;
import co.animalMate.board.command.SitterFormViewAction;
import co.animalMate.board.command.SitterListAction;
import co.animalMate.board.command.SitterListSearhAction;
import co.animalMate.board.command.boardTradePetAction;
import co.animalMate.login.command.FindIdAction;
import co.animalMate.login.command.FindPwAction;
import co.animalMate.login.command.JoinForm;
import co.animalMate.login.command.JoinInsertAction;
import co.animalMate.login.command.LoginAction;
import co.animalMate.login.command.LoginForm;
import co.animalMate.login.command.LogoutAction;
import co.animalMate.login.command.ResetPwAction;
import co.animalMate.login.command.SitterDeleteAction;
import co.animalMate.login.command.idOverlapCheck;
import co.animalMate.main.command.AdminLoginAction;
import co.animalMate.main.command.AjaxHowMuchMessageAction;
import co.animalMate.main.command.AjaxHowMuchQuestionAction;
import co.animalMate.main.command.AjaxMessageInfoAction;
import co.animalMate.main.command.AjaxPetInfoAction;
import co.animalMate.main.command.ApplySitterFormAction;
import co.animalMate.main.command.EnvironmentImgAction;
import co.animalMate.main.command.EnvironmentImgDeleteAction;
import co.animalMate.main.command.GoBlackBoardPageAction;
import co.animalMate.main.command.GoLoginPageAction;
import co.animalMate.main.command.GoMessageFormAction;
import co.animalMate.main.command.GoMessageListPageAction;
import co.animalMate.main.command.GoMypagePageAction;
import co.animalMate.main.command.GoNoticeBoardPageAction;
import co.animalMate.main.command.GoNoticeFormAction;
import co.animalMate.main.command.GoOwnerListPageAction;
import co.animalMate.main.command.GoPetSelectFormAction;
import co.animalMate.main.command.GoSendMessageListPageAction;
import co.animalMate.main.command.GoSitterListPageAction;
import co.animalMate.main.command.GotradeListPageAction;
import co.animalMate.main.command.InsertEnvironmentImgAction;
import co.animalMate.main.command.OwnerDeleteAction;
import co.animalMate.main.command.OwnerFormViewApplyAction;
import co.animalMate.main.command.OwnerUpdateAction;
import co.animalMate.main.command.SitterInsertAction;
import co.animalMate.main.command.SitterUpdateAction;
import co.animalMate.main.command.SubmitMessageAction;
import co.animalMate.main.command.SubmitNoticeAction;
import co.animalMate.main.command.SubmitReportAction;
import co.animalMate.mypage.command.BookmarkAction;
import co.animalMate.mypage.command.CommentView;
import co.animalMate.mypage.command.JoblistUpdateAction;
import co.animalMate.mypage.command.MemberForm;
import co.animalMate.mypage.command.MemberFormUpdateAction;
import co.animalMate.mypage.command.MypetDeleteAction;
import co.animalMate.mypage.command.MypetForm;
import co.animalMate.mypage.command.MypetFormAction;
import co.animalMate.mypage.command.MypetList;
import co.animalMate.mypage.command.MypetUpdate;
import co.animalMate.mypage.command.MypetUpdateAction;
import co.animalMate.mypage.command.MytradeFinishAction;
import co.animalMate.mypage.command.MytradeList;
import co.animalMate.mypage.command.OwnerFormCheck;
import co.animalMate.mypage.command.OwnerTradeCheck;
import co.animalMate.mypage.command.OwnerTradeCheckAction;
import co.animalMate.mypage.command.PointChargingAction;
import co.animalMate.mypage.command.PointChargingView;
import co.animalMate.mypage.command.Profile;
import co.animalMate.mypage.command.SitterFormCheck;
import co.animalMate.mypage.command.SitterTradeCheck;
import co.animalMate.mypage.command.SitterTradeCheckAction;



public class FrontController extends HttpServlet {
   private static final long serialVersionUID = 1L;
    private HashMap<String, Action> map = new HashMap<String, Action>();

    public FrontController() {
        super();
    }

   public void init(ServletConfig config) throws ServletException { //init은 처음 실행할 때 한 번만 실행됨
      // 요청들을 정의함
      //상민      
      map.put("/main.do", new MainAction()); // 처음 들어오는 페이지 처리 index.jsp
      map.put("/goLoginPage.do", new GoLoginPageAction()); // 헤더에서 로그인 버튼 눌렀을 때
      map.put("/gotradeListPage.do", new GotradeListPageAction()); // 헤더에서 요청알림 버튼 눌렀을 때
      map.put("/goMessageListPage.do", new GoMessageListPageAction()); // 헤더에서 쪽지 버튼 눌렀을 때
      map.put("/goSendMessageListPage.do", new GoSendMessageListPageAction()); // 쪽지페이지에서 보낸 쪽지함 버튼 눌렀을 때
      map.put("/goNoticeBoardPage.do", new GoNoticeBoardPageAction()); // 헤더에서 공지사항 메뉴 눌렀을 때
      map.put("/goOwnerListPage.do", new GoOwnerListPageAction()); // 헤더에서 돌봐주세요 메뉴 눌렀을 때
      map.put("/goSitterListPage.do", new GoSitterListPageAction()); // 헤더에서 돌봐줄께요 메뉴 눌렀을 때
      map.put("/goMypagePage.do", new GoMypagePageAction()); // 헤더에서 마이페이지 메뉴 눌렀을 때
      map.put("/goBlackBoardPage.do", new GoBlackBoardPageAction()); // 신고하기 폼에서 제출하기 버튼을 눌렀을 때 
      map.put("/adminLogin.do", new AdminLoginAction()); // 로그인 창에서 admin으로 로그인 했을 때
      map.put("/goNoticeForm.do", new GoNoticeFormAction()); // 공지사항 페이지에서 글쓰기 버튼 눌렀을 때
      map.put("/goMessageForm.do", new GoMessageFormAction()); // 메세지 리스트에서 메세지를 눌렀을 때
      map.put("/goPetSelectForm.do", new GoPetSelectFormAction());//시터폼뷰에서 예약하기 버튼 눌렀을 때
      map.put("/environmentImg.do", new EnvironmentImgAction());//마이페이지에서 돌봄환경 버튼 눌렀을 때
         //제출하기
      map.put("/submitReport.do", new SubmitReportAction()); // 신고폼 페이지에서 제출버튼 눌렀을 때
      map.put("/submitNotice.do", new SubmitNoticeAction()); // 공지사항폼 페이지에서 제출버튼 눌렀을 때
      map.put("/sitterInsert.do", new SitterInsertAction()); // 시터폼에서 제출하기 눌렀을 때
      map.put("/sitterUpdate.do", new SitterUpdateAction()); // 시터폼에서 수정하기 눌렀을 때
      map.put("/sitterDelete.do", new SitterDeleteAction()); // 시터폼뷰에서 삭제하기 눌렀을 때
      map.put("/submitMessage.do", new SubmitMessageAction()); // 메세지 보내기폼에서 제출하기 눌렀을 때
      map.put("/ownerUpdate.do", new OwnerUpdateAction()); // 오너폼에서 수정하기 눌렀을 때
      map.put("/ownerDelete.do", new OwnerDeleteAction()); // 오너폼뷰에서 삭제하기 버튼 눌렀을 때
      map.put("/ownerFormViewApply.do", new OwnerFormViewApplyAction()); // 오너폼뷰에서 지원하기 버튼 눌렀을 때
      map.put("/applySitterForm.do", new ApplySitterFormAction()); //petSelectForm에서 예약하기 버튼 눌렀을 때
      map.put("/insertEnvironmentImg.do", new InsertEnvironmentImgAction()); //돌봄환경 사진 등록하기 버튼 눌렀을 때
      map.put("/environmentImgDelete.do", new EnvironmentImgDeleteAction()); //돌봄환경 사진 삭제하기 버튼 눌렀을 때
      
         //아작스
      map.put("/ajax/petInfo.do", new AjaxPetInfoAction()); //펫 목록 화면 호출
      map.put("/ajax/messageInfo.do", new AjaxMessageInfoAction()); //메세지 리스트에서 메세지 눌렀을 때
      map.put("/ajax/howMuchMessage.do", new AjaxHowMuchMessageAction()); //메인테마 메세지 갯수 표시
      

      
      
      
      
      
      
      
      
      //찬호
     map.put("/loginForm.do", new LoginForm()); //로그인 폼 띄우기
     map.put("/login.do", new LoginAction()); //로그인 메뉴를 처리하는 것
     map.put("/joinForm.do", new JoinForm()); //회원가입 폼 띄우기
     map.put("/joinInsert.do", new JoinInsertAction()); //회원입력
     map.put("/logout.do", new LogoutAction()); //로그아웃 액션
     map.put("/id_find.do", new FindIdAction()); //아이디 찾기 액션
     map.put("/findpw.do", new FindPwAction()); //비밀번호 찾기 액션
     map.put("/resetPw.do", new ResetPwAction()); //비밀번호 변경 액션
     map.put("/ajax/idoverlapcheck.do", new idOverlapCheck()); //아이디중복확인 찾기 액션
     map.put("/memberUpdate.do", new MemberFormUpdateAction()); // 회원정보수정 액션
     map.put("/ajax/bookmark.do", new BookmarkAction()); // 즐겨찾기 추가
   

      
      
      
      
      
      
      
     //형준
     map.put("/ajax/memberSearch.do",new MemberSearchAction());//검색 하는 회원찾기
     map.put("/ajax/memberlist.do",new MemberListAction());
     map.put("/ajax/blackSearch.do",new BlackSearchAction());
     map.put("/ajax/updateblack.do",new BlackUpdateAction());
     map.put("/ajax/blacklist.do",new BlackListAction());
     map.put("/tradelist.do",new tradeAction());
     map.put("/ajax/updatemember.do", new MemberUpdateAction());//업데이트
     map.put("/submitQuestion.do",new SubmitQuestionAction());
     map.put("/ajax/sitterlist.do",new SitterlistAction());
     map.put("/goQuestionBoard.do",new GoQuestionBoardPageAction());
     map.put("/questionlist.do",new QuestionlistAction());
     map.put("/questionselect.do",new QuestionselectAction());
     map.put("/sitterinsert.do",new SitterInsertlistAction());
     map.put("/questionrewrite.do",new QuestionRewriteAction());
     map.put("/questionanswer.do",new QuestionAnswerAction());
     map.put("/ajax/sitterSearch.do",new SitterSearchAction());
     map.put("/reviewinsert.do",new ReviewInsertAction());
     map.put("/reviewlist.do",new ReviewlistAction());
     map.put("/ajax/result.do",new Result());
     



      
      
      
      
      
      
      
     //선애
     map.put("/ownerList.do", new OwnerListAction()); //ownerList 화면 호출
     map.put("/ownerForm.do", new OwnerFormAction()); //OwnerForm 화면 호출
     map.put("/ownerInsert.do", new OwnerInsertAction()); //OwnerForm Insert 
     map.put("/ownerListSearch.do", new OwnerListSearhAction()); //ownerList에서 검색
     map.put("/ownerFormSelect.do", new OwnerFormViewAction()); //OwnerFormView 화면 호출
     map.put("/sitterList.do", new SitterListAction()); //sitterList 화면 호출
     map.put("/sitterForm.do", new SitterFormAction()); //sitterForm 화면 호출
     map.put("/sitterFormSelect.do", new SitterFormViewAction()); //OwnerFormView 화면 호출
     map.put("/sitterListSearch.do", new SitterListSearhAction()); //sitterList에서 검색 
     map.put("/boardTradePet.do", new boardTradePetAction()); //ownerForm 에서 거래신청


      
      
      
      
   
     //진구
     map.put("/mypetForm.do", new MypetForm()); //펫 등록 화면 호출
     map.put("/mypetFormAction.do", new MypetFormAction()); //펫 등록 액션
     map.put("/mypetList.do", new MypetList()); //펫 목록 화면 호출
     map.put("/mypetUpdate.do", new MypetUpdate()); //펫 수정, 삭제 화면 호출       
     map.put("/mypetUpdateAction.do", new MypetUpdateAction()); //펫 수정 액션
     map.put("/mypetDeleteAction.do", new MypetDeleteAction()); //펫 삭제 액션
     map.put("/mytradeList.do", new MytradeList()); //내 거래현황,내역 화면 호출
     map.put("/ownerFormCheck.do", new OwnerFormCheck()); // 돌봐주세요 화면 호출
     map.put("/sitterFormCheck.do", new SitterFormCheck()); // 돌봐줄게요 화면 호출
     map.put("/memberForm.do", new MemberForm()); //회원 정보 수정
     map.put("/profile.do", new Profile()); //프로필 화면 호출
     map.put("/ownerTradeCheck.do", new OwnerTradeCheck()); // 돌봐주세요 거래 체결 화면 호출
     map.put("/ownerTradeCheckAction.do", new OwnerTradeCheckAction()); // 돌봐주세요 거래 선택후 체결 액션
     map.put("/sitterTradeCheck.do", new SitterTradeCheck()); // 돌봐줄게요 거래 체결 화면 호출
     map.put("/sitterTradeCheckAction.do", new SitterTradeCheckAction()); // 돌봐주세요 거래 선택후 체결 액션 
     map.put("/mytradeFinishAction.do", new MytradeFinishAction()); // 거래종료와 시터에게 돈주는 액션
     map.put("/joblistUpdateAction.do", new JoblistUpdateAction()); // 거래종료와 시터에게 돈주는 액션
     map.put("/pointChargingView.do", new PointChargingView()); // 포인트 화면 가기
     map.put("/pointChargingAction.do", new PointChargingAction()); // 포인트 충전 액션
     map.put("/commentView.do", new CommentView()); // 리뷰 상세보기
     

   }
   
   //컨트롤러로 넘어오면 서비스 메소드 실행
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // 수행할 명령을 정리
      request.setCharacterEncoding("utf-8"); //한글깨짐 방지!
      response.setContentType("text/html;charset=UTF-8");//한글깨짐 방지!
      String uri = request.getRequestURI();
      String contextPath = request.getContextPath();
      String path = uri.substring(contextPath.length()); //실제 들어오는 요청페이지를 찾음(uri에서 contextPath를 지움)
      
      Action command = map.get(path); //init메소드 값을 가져온다!
      String viewPage = command.exec(request, response); //명령어가 수행되고 나서 보여줄 페이지 선택

     if (viewPage != null) {
         RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage); // 선택한 페이지로 가기
         dispatcher.forward(request, response);
     }   
   }
}

      

package kr.or.team3.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.team3.action.Action;
import kr.or.team3.action.ActionForward;
import kr.or.team3.service.GosuMap_Service;
import kr.or.team3.service.Gosu_RQ_CompleteOK_Service;
import kr.or.team3.service.Gosu_RQ_DeleteOk_Service;
import kr.or.team3.service.Gosu_register_Info_Edit_Sevice;
import kr.or.team3.service.Gosuregister_Service;
import kr.or.team3.service.Member_EditOk_Service;
import kr.or.team3.service.Member_JoinOk_Service;

import kr.or.team3.service.Member_RQ_Delete_Service;
import kr.or.team3.service.Member_RQ_EditOk;
import kr.or.team3.service.Notice_Del_Service;
import kr.or.team3.service.Notice_Edit_Service;
import kr.or.team3.service.Notice_File_Download;
import kr.or.team3.service.Notice_Write_Service;
import kr.or.team3.service.QnA_Edit_Service;
import kr.or.team3.service.QnA_Wirte_Service;
import kr.or.team3.service.RQ_Form_WriteOk_Service;
import kr.or.team3.service.Review_Write_Severvice;

@WebServlet("*.go")
public class gosuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public gosuController() {
        super();
    }
    
    
    
    protected void doprocess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	String requesturl = request.getRequestURI();
    	String requestpath = request.getContextPath();
    	String URL = requesturl.substring(requestpath.length());
    	
    	System.out.println(URL);
    	
    	Action action = null;
    	ActionForward actionForward = null;
    	
    	// 메인화면
    	if (URL.equals("/main.go")) {
    		actionForward = new ActionForward();
    		actionForward.setPath("main.jsp");
    	
    	// 회원가입
    	} else if(URL.equals("/Join.go")) {
    		actionForward = new ActionForward();
    		actionForward.setPath("/WEB-INF/views/register/Join.jsp");
    	
    	// 회원가입 확인
    	} else if(URL.equals("/Joinok.go")) {
    		action = new Member_JoinOk_Service();
    		actionForward = action.excute(request, response);
    	
    	// 로그인  ( 로그인 확인은 ajax )
    	} else if(URL.equals("/Login.go")) {
    		actionForward = new ActionForward();
    		actionForward.setPath("/WEB-INF/views/register/Login.jsp");
    		
    	// ==================================================================================	
    	// 고수가입하기_1 화면	
    	} else if(URL.equals("/Gosuregister_1.go")) {
    		actionForward = new ActionForward();
    		actionForward.setPath("/WEB-INF/views/register/GosuRegister/Gosuregister_1.jsp");
    	// 고수가입하기_2 화면	
    	} else if(URL.equals("/Gosuregister_2.go")) {
			actionForward = new ActionForward();
			actionForward.setPath("/WEB-INF/views/register/GosuRegister/Gosuregister_2.jsp");
        
    	// 고수가입완료
    	} else if(URL.equals("/GosuregisterOk.go")) {
    		action = new Gosuregister_Service();
    		actionForward = action.excute(request, response);
    	
    	// 고수 프로필
    	} else if(URL.equals("/Gosuregister_Info.go")) {
    		actionForward = new ActionForward();
			actionForward.setPath("/WEB-INF/views/register/GosuRegister/Gosuregister_Info.jsp");
		
		// 고수 프로필 수정	
    	} else if(URL.equals("/Gosuregister_Info_Edit.go")) {
    		action = new Gosu_register_Info_Edit_Sevice();
    		actionForward = action.excute(request, response);
			
    		// ==================================================================================
    	// 검색한 고수 프로필 	
    	} else if(URL.equals("/searchGosu.go")) {
    		actionForward = new ActionForward();
			actionForward.setPath("/WEB-INF/views/gosu_page/searchgosu.jsp");
			
		// 고수 프로필 가기
    	} else if(URL.equals("/GosuProfile.go")) {
    		actionForward = new ActionForward();
			actionForward.setPath("/WEB-INF/views/gosu_page/Gosupage.jsp");	
    		
    	// 마이페이지	
    	} else if(URL.equals("/Mypage.go")) {
    		actionForward = new ActionForward();
    		actionForward.setPath("/WEB-INF/views/register/Mypage.jsp");
    	
    	// 회원정보수정
    	} else if(URL.equals("/EditOk.go")) {
    		action = new Member_EditOk_Service();
    		actionForward = action.excute(request, response);
    	
    	// 요청서 작성완료 by 안승주
    	}else if(URL.equals("/SendRQOk.go")) {
    		action = new RQ_Form_WriteOk_Service();
    		actionForward = action.excute(request, response);
    		
    	// 요청서 작성하기 by 안승주
    	}else if(URL.equals("/WriteRQ.go")) {
    		actionForward = new ActionForward();
    		actionForward.setPath("/WEB-INF/views/RQ/RQ_Member_write.jsp");
    		
    	// (고객) 요청서 가져오기 by 안승주
    	}else if(URL.equals("/RQList_Member.go")) {
    		actionForward = new ActionForward();
    		actionForward.setPath("/WEB-INF/views/RQ/RQ_Member/RQ_List.jsp");

    	//고수위치	
    	} else if(URL.equals("/gosumap.go")) {
    		action = new GosuMap_Service();
    		actionForward = action.excute(request, response);
    	
    	// (고객) 요청서 자세히 보기 by 안승주
    	}else if(URL.equals("/Rq_Content_Memeber.go")) {
    		actionForward = new ActionForward();
    		actionForward.setPath("/WEB-INF/views/RQ/RQ_Member/RQ_Content_Member.jsp");
    	
    	// (고수) 요청서 가져오기 by 안승주
    	}else if(URL.equals("/RQList_Gosu.go")) {
    		actionForward = new ActionForward();
    		actionForward.setPath("/WEB-INF/views/RQ/RQ_Gosu/RQ_List_Gosu.jsp");
    		
    	// (고수) 요청서 자세히보기 by 안승주
    	}else if(URL.equals("/RQ_Content_Gosu.go")) {
    		actionForward = new ActionForward();
    		actionForward.setPath("/WEB-INF/views/RQ/RQ_Gosu/RQ_Content_Gosu.jsp");
    	     
        // (고수) 요청서 삭제 by 안승주
    	}else if(URL.equals("/Gosu_RQ_DeleteOk.go")) {
    		action = new Gosu_RQ_DeleteOk_Service();
    		actionForward = action.excute(request, response);
    		
    	// (고수) 요청서 완료 by 안승주
    	}else if(URL.equals("/Gosu_RQ_CompleteOk.go")) {
    		action = new Gosu_RQ_CompleteOK_Service();
    		actionForward = action.excute(request, response);
    		
    	// (고객)	 요청서 삭제 by 안승주
    	}else if(URL.equals("/Member_RQ_DeleteOk.go")) {
    		action = new Member_RQ_Delete_Service();
    		actionForward = action.excute(request, response);
    		
    	// (고객) 요청서 수정페이지 이동 by 안승주
    	}else if(URL.equals("/Member_RQ_Edit.go")) {
    		actionForward = new ActionForward();
    		actionForward.setPath("/WEB-INF/views/RQ/RQ_Member/RQ_Content_Edit_Member.jsp");
    
    	// 자주하는질문 글쓰기로 이동
    	}else if(URL.equals("/QnAwrite.go")) {
    		actionForward = new ActionForward();
    		actionForward.setPath("/WEB-INF/views/gosu_page/QnA_Write.jsp");	
    		
    	// 자주하는질문 글쓰기
    	}else if(URL.equals("/QnAwriteOK.go")) {
    		action = new QnA_Wirte_Service();
    		actionForward = action.excute(request, response);
    		
    	// 자주하는질문 수정하기로 이동
    	}else if(URL.equals("/QnAEdit.go")) {
    		actionForward = new ActionForward();
    		actionForward.setPath("/WEB-INF/views/gosu_page/QnA_Edit.jsp");	

        // 자주하는질문 수정하기
    	}else if(URL.equals("/QnAEditOK.go")) {
    		action = new QnA_Edit_Service();
    		actionForward = action.excute(request, response);
    	
    	// 공지사항 글쓰기로 이동
    	}else if(URL.equals("/NoticeWrite.go")) {
    		actionForward = new ActionForward();
    		actionForward.setPath("/WEB-INF/views/gosu_page/Notice_Write.jsp");		
    		
    	// 공지사항 글쓰기
		}else if(URL.equals("/NoticeWriteOK.go")) {
			  action = new Notice_Write_Service();
			  actionForward = action.excute(request, response);
			  
		// (고객) 요청서 수정 by 안승주
		}else if(URL.equals("/Member_RQ_EditOk.go")) {
			action = new Member_RQ_EditOk();
			actionForward = action.excute(request, response);

    	// 공지사항 상세보기로 이동
		}else if(URL.equals("/NoticeContent.go")) {
			actionForward = new ActionForward();
			actionForward.setPath("/WEB-INF/views/gosu_page/Notice_Content.jsp");		
    	
    	// 공지사항 수정으로 이동
		}else if(URL.equals("/NoticeEdit.go")) {
			actionForward = new ActionForward();
			actionForward.setPath("/WEB-INF/views/gosu_page/Notice_Edit.jsp");		
		 
		// 공지사항 수정
		}else if(URL.equals("/NoticeEditOK.go")) {
			action = new Notice_Edit_Service();
			actionForward = action.excute(request, response);
			
		// 공지사항 삭제 s
		}else if(URL.equals("/NoticeDel.go")) {
			action = new Notice_Del_Service();
			actionForward = action.excute(request, response);
			
    	// 공지사항 파일 다운로드 	
		} else if(URL.equals("/NoticeFileDownload.go")) {
			action = new Notice_File_Download();
			actionForward = action.excute(request, response);
		
    	// 리뷰쓰기폼으로 이동
		}else if(URL.equals("/ReviewWrite.go")) {
			actionForward = new ActionForward();
			actionForward.setPath("/WEB-INF/views/gosu_page/Review_Write.jsp");
		
    	// 리뷰쓰기
		} else if(URL.equals("/ReviewWriteOk.go")) {
			action = new Review_Write_Severvice();
			actionForward = action.excute(request, response);		
		}
    	
    	

    	// 결과 forward
    	if(actionForward != null) {
            RequestDispatcher dis = request.getRequestDispatcher(actionForward.getPath());
            dis.forward(request, response);
       
         }
    	
    	
  
    	
    	
    	
    	
    	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doprocess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doprocess(request, response);
	}

}

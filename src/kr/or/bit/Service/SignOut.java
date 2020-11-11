package kr.or.bit.Service;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.bit.Action.Action;
import kr.or.bit.Action.ActionForward;



public class SignOut implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		String msg = "로그아웃";
		String url = "mainpage.do";
		
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/WEB-INF/common/redirect.jsp");

		return forward;
	}

}

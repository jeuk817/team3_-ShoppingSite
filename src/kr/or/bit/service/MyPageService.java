package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.model.dao.DAOMember;
import kr.or.bit.model.dto.DTOMember;

public class MyPageService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		System.out.println("ddd");
		
		String id = (String)request.getSession().getAttribute("memberId");
		DTOMember member = DAOMember.getMemberById(id);
//		DTOMember member = new DTOMember("pooreum", "1234", "푸름", "010-1234-1234", "1234-1234", "인천 어딘가", "Y");
		request.setAttribute("member", member);
		
		forward.setRedirect(false);
		forward.setPath("MyPage.jsp");
		
		return forward;
	}

}

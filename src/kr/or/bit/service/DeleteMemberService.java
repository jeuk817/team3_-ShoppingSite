package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.model.dao.DAOMember;
import kr.or.bit.model.dto.DTOMember;

public class DeleteMemberService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memberId");
		DTOMember member = DAOMember.getMemberById(id);
		request.setAttribute("DEL_FLAG", "y");
	
		
		forward.setRedirect(false);
		forward.setPath("Main.jsp");
		
		return forward;
	}

}

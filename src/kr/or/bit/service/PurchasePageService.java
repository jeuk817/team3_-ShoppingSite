package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.model.dao.DAOMember;
import kr.or.bit.model.dto.DTOMember;

public class PurchasePageService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
//		JsonArray products = (JsonArray)request.getAttribute("jsonBody");
		String id = (String)request.getSession().getAttribute("memberId");
		DTOMember member = DAOMember.getMemberById(id);
//		request.setAttribute("products", products);
		request.setAttribute("member", member);
		
		forward.setRedirect(false);
		forward.setPath("Purchase.jsp");
//		forward.setPath("Test.jsp");
		
		return forward;
	}

}

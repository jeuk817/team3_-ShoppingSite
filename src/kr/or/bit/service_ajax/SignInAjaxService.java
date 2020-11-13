package kr.or.bit.service_ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import kr.or.bit.action.ActionAjax;
import kr.or.bit.action.ActionAjaxData;
import kr.or.bit.model.dao.DAOMember;
import kr.or.bit.model.dto.DTOMember;
import kr.or.bit.utils.c_Salt;
import kr.or.bit.utils.c_SHAUtil;


public class SignInAjaxService implements ActionAjax {

	@Override
	public ActionAjaxData execute(HttpServletRequest request, HttpServletResponse response) {

		c_SHAUtil sha =new c_SHAUtil();
		c_Salt salt = new c_Salt();
		String s = salt.readSalt("key.txt");

		
		ActionAjaxData ajaxData = new ActionAjaxData();
		
		JsonObject body = (JsonObject)request.getAttribute("jsonBody");
		String id = body.get("id").getAsString();
		String pwd = body.get("pwd").getAsString();
		
		DTOMember member = DAOMember.getMemberById(id);
		if(member == null || !sha.getSha512(s+pwd).equals(member.getPwd())) {
			ajaxData.setData("fail");
		} else {
			HttpSession session = request.getSession();
            session.setAttribute("memberId", member.getId());
            session.setMaxInactiveInterval(15 * 60);
            
            ajaxData.setData("success");
		}
		ajaxData.setContentType("text/plain");
		
		return ajaxData;
	}

}

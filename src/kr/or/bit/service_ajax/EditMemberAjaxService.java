package kr.or.bit.service_ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import kr.or.bit.action.ActionAjax;
import kr.or.bit.action.ActionAjaxData;
import kr.or.bit.model.dao.DAOMember;
import kr.or.bit.model.dto.DTOMember;

public class EditMemberAjaxService implements ActionAjax {

	@Override
	public ActionAjaxData execute(HttpServletRequest request, HttpServletResponse response) {
		ActionAjaxData ajaxData = new ActionAjaxData();
//		{address:string,
//			pwd:string,
//			card:string,
//			hp:string}
		JsonObject jsonBody = (JsonObject)request.getAttribute("jsonBody");
		String pwd = jsonBody.get("pwd").getAsString();
		String hp = jsonBody.get("hp").getAsString();
		String cardNum = jsonBody.get("card_num").getAsString();
		String address = jsonBody.get("address").getAsString();
		String id = (String)request.getAttribute("id");
		DTOMember member = new DTOMember(id, pwd, hp, cardNum, address);
		int resultRow = DAOMember.updateMember(member);
		
		if(resultRow == 1) ajaxData.setData("success");
		else ajaxData.setData("fail");
		
		ajaxData.setContentType("text/plain");
		
		return ajaxData;
	}

}

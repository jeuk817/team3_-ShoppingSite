package kr.or.bit.service_ajax;

import com.google.gson.JsonObject;
import kr.or.bit.action.ActionAjax;
import kr.or.bit.action.ActionAjaxData;
import kr.or.bit.model.dao.DAOMember;
import kr.or.bit.model.dto.DTOMember;
import kr.or.bit.utils.c_SHAUtil;
import kr.or.bit.utils.c_Salt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteMemberAjaxService implements ActionAjax {

    @Override
    public ActionAjaxData execute(HttpServletRequest request, HttpServletResponse response) {
        ActionAjaxData ajaxData = new ActionAjaxData();
        
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("memberId");
        JsonObject body = (JsonObject) request.getAttribute("jsonBody");
        String pwd = body.get("pwd").getAsString();

        c_Salt salt = new c_Salt();
        c_SHAUtil sha = new c_SHAUtil();
        String s = salt.readSalt("key.txt");
        
        DTOMember member = DAOMember.getMemberById(id);
        if(member == null || !sha.getSha512(s+pwd).equals(member.getPwd())) {
			ajaxData.setData("fail");
		} else {
			DAOMember.lim_DeleteMember(id);
			session.invalidate();
			ajaxData.setData("success");
		}
        ajaxData.setContentType("text/plain");

        return ajaxData;
    }
}

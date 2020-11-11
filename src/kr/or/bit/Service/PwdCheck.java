package kr.or.bit.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.Action.Action;
import kr.or.bit.Action.ActionForward;
import kr.or.bit.model.DAO.MemberDAO;

public class PwdCheck implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		String userPwd = request.getParameter("pwd");
		
		MemberDAO memberdao = null;
		try {
			memberdao = new MemberDAO();
	
			String result = memberdao.pwdCheck(userPwd);
	
			if(result.equals("fail")) { //비밀번호가 다른경우
				result = "fail";

			}
			if(result.equals("success")) { //비밀번호 일치
				result = "success";

			}if(result.equals("fail2")) { //회원이 x
				result = "fail";

			}
			request.setAttribute("result", result);
			forward = new ActionForward();
			forward.setPath("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
}

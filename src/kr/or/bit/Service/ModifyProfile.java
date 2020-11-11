package kr.or.bit.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.Action.Action;
import kr.or.bit.Action.ActionForward;
import kr.or.bit.model.DAO.MemberDAO;
import kr.or.bit.model.DTO.MemberDTO;


public class ModifyProfile implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		MemberDAO memberdao = null;
		ActionForward forward = null;
		MemberDTO memberdto = null;
		int resultrow = 0;
		String userId =  (String)request.getSession().getAttribute("id");
		String Pwd = request.getParameter("pwd");
		String name = request.getParameter("name");	
		String hp = request.getParameter("hp");	
		String address = request.getParameter("address");
		String card = request.getParameter("card");		
	
		try {
			memberdao = new MemberDAO();

			memberdto = memberdao.memberSelect(userId);
			
			if(memberdto!=null) {
				memberdto.setPwd(Pwd);
				memberdto.setName(name);
				memberdto.setName(hp);
				memberdto.setName(address);
				memberdto.setName(card);
				
				
				resultrow = memberdao.modifyProfile(memberdto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("resultrow", resultrow);
		forward = new ActionForward();
		forward.setPath("/ajaxpath/result_row.jsp");
		return forward;
	}

}

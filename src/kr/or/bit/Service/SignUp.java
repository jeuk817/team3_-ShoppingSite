package kr.or.bit.Service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.Action.Action;
import kr.or.bit.Action.ActionForward;
import kr.or.bit.model.DAO.MemberDAO;
import kr.or.bit.model.DTO.MemberDTO;




public class SignUp implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)  {
		System.out.println("회원가입시작");
		ActionForward forward = new ActionForward();
		MemberDAO memberDao = new MemberDAO();
		MemberDTO memberDto = new MemberDTO();
		
		int result=0;		
   		String msg ="";   		
   		memberDto.setId(request.getParameter("id"));
   		memberDto.setPwd(request.getParameter("pwd"));
   		memberDto.setName(request.getParameter("name"));
   		memberDto.setHp(request.getParameter("hp"));
   		memberDto.setAddress(request.getParameter("address"));
   		memberDto.setCard(request.getParameter("card"));
   		
   		try {
			result = memberDao.signUp(memberDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	
   		if(result>0){
   			//회원가입성공
   			msg = "success";
   		
   		} else {
	   		//회원가입실패
   			msg= "fail";
   		}
   		request.setAttribute("msg", msg);	   		
   		
   		forward.setRedirect(false);
   		forward.setPath("/WEB-INF/views/main.jsp");
   		
   		return forward;
   		}
	


}
